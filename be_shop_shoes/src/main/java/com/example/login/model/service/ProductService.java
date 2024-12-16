package com.example.login.model.service;

import com.example.login.Dao.ProductColorDao;
import com.example.login.Dao.ProductSizeDao;
import com.example.login.dto.*;
import com.example.login.dto.mapper.InventoryMapper;
import com.example.login.dto.mapper.ProductColorMapper;
import com.example.login.dto.mapper.ProductMapper;
import com.example.login.dto.product.*;
import com.example.login.model.entity.*;
import com.example.login.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileUrlProduct fileUrlProduct;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private final ProductMapper productMapper;

    private final ProductColorMapper productColorMapper;

    private final InventoryMapper inventoryMapper;

    public ProductService(ProductMapper productMapper,
                          ProductColorMapper productColorMapper,
                          InventoryMapper inventoryMapper) {
        this.productMapper = productMapper;
        this.productColorMapper = productColorMapper;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional
    public void createProduct(ProductDto productDto) {

        // tao moi san pham
        if (productDto.getId() == null){
            if (productRepository.findByProductCode(productDto.getProductCode()).isPresent()) {
                throw new IllegalArgumentException("Product code already exists: " + productDto.getProductCode());
            }
            //Luu thong tin chung product;
            Product product = new Product();
            product.setCategoryId(productDto.getCategoryId());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setProductCode(productDto.getProductCode());
            product.setBranchId(productDto.getBranchId());
            product = productRepository.save(product);

            // Luu thong tin vao productColor;
            List<ProductDetailDto> productDetailDtoList = productDto.getProductDetail();
            Integer order = 1;
            for (ProductDetailDto item : productDetailDtoList) {
                ProductColor productColor = new ProductColor();
                productColor.setProductId(product.getId());
                productColor.setColorId(item.getColorId());
                productColor = productColorRepository.save(productColor);

                // Luu anh cua mau
                FileProductImg fileProductImg = new FileProductImg();
                fileProductImg.setProductColorId(productColor.getId());
                fileProductImg.setFileUrl(item.getFileUrl());
                fileProductImg.setOrder(Long.valueOf(order));
                fileProductImg.setPublicId(item.getPublicId());
                fileProductImg.setSignature(item.getSignature());
                fileProductImg.setFileName(item.getFileName());
                fileUrlProduct.save(fileProductImg);

                // Luu iventory
                List<InventoryDto> inventoryDtoList = item.getInventory();
                for (InventoryDto i : inventoryDtoList){
                    Inventory inventory = new Inventory();
                    inventory.setColorId(item.getColorId());
                    inventory.setSizeId(i.getSizeId());
                    inventory.setQuantity(i.getQuantity());
                    inventory.setProductId(product.getId());
                    inventoryRepository.save(inventory);
                }

                order++;
            }


        }else {
            // cap nhat san pham
            // tim sp theo id trong db
            Optional<Product> product = productRepository.findById(productDto.getId());
            if (product.isPresent()){
                // set data
                Product dataSave = product.get();
                dataSave.setProductCode(productDto.getProductCode());
                dataSave.setName(productDto.getName());
                dataSave.setCategoryId(productDto.getCategoryId());
                dataSave.setDescription(productDto.getDescription());
                dataSave.setPrice(productDto.getPrice());
                dataSave.setBranchId(productDto.getBranchId());

                // luu cap nhat
                productRepository.save(dataSave);

                // cap nhat thong tin chi tiet
                // Luu thong tin vao productColor;
                List<ProductDetailDto> productDetailDtoList = productDto.getProductDetail();
                // loc ra file anh moi
                List<String> listNewPublicId = new ArrayList<>();
                productDetailDtoList.forEach(i ->{
                    listNewPublicId.add(i.getPublicId());
                });
                // loc ra file anh cu
                List<FileProductProjection> fileOld = fileUrlProduct.filePublicOld(productDto.getId());

                // lisst anh se xoa khi tcap nhat
                List<FileProductProjection> fileDelete = new ArrayList<>();

                // loc lay anh xoa
                fileOld.forEach(i ->{
                    // xoa anh cu
                    if(!listNewPublicId.contains(i.getPublicId())){
                        fileDelete.add(i);
                    }
                });

                // xoa thong tin luu truoc do
                // trong product_color
                List<ProductColor> productColors = productColorRepository.findByProductId(productDto.getId());
                List<Long> productColorIds = productColors.stream()
                        .map(ProductColor::getId) // Chuyển mỗi ProductColor thành id của nó
                        .collect(Collectors.toList());
                // trong file
                List<FileProductImg> fileProductImgs = fileUrlProduct.findByProductColorIdIn(productColorIds);
                // trong inventory
                List<Inventory> inventories = inventoryRepository.findByProductId(productDto.getId());

                // xoa thong tin
                productColorRepository.deleteAll(productColors);
                fileUrlProduct.deleteAll(fileProductImgs);
                inventoryRepository.deleteAll(inventories);

                // luu lai thong tin
                // Luu thong tin vao productColor;
                Integer order = 1;
                for (ProductDetailDto item : productDetailDtoList) {
                    ProductColor productColor = new ProductColor();
                    productColor.setProductId(product.get().getId());
                    productColor.setColorId(item.getColorId());
                    productColor = productColorRepository.save(productColor);

                    // Luu anh cua mau
                    FileProductImg fileProductImg = new FileProductImg();
                    fileProductImg.setProductColorId(productColor.getId());
                    fileProductImg.setFileUrl(item.getFileUrl());
                    fileProductImg.setOrder(Long.valueOf(order));
                    fileProductImg.setPublicId(item.getPublicId());
                    fileProductImg.setSignature(item.getSignature());
                    fileProductImg.setFileName(item.getFileName());
                    fileUrlProduct.save(fileProductImg);

                    // Luu iventory
                    List<InventoryDto> inventoryDtoList = item.getInventory();
                    for (InventoryDto i : inventoryDtoList){
                        Inventory inventory = new Inventory();
                        inventory.setColorId(item.getColorId());
                        inventory.setSizeId(i.getSizeId());
                        inventory.setQuantity(i.getQuantity());
                        inventory.setProductId(product.get().getId());
                        inventoryRepository.save(inventory);
                    }

                    order++;
                }

                // xoa anh thua tren clound
                fileDelete.forEach(img ->{
                    try {
                        cloudinaryService.deleteImage(img.getPublicId());
                    } catch (Exception e) {
                        System.out.printf(e.getMessage());
                    }
                });

            }

        }

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.getAllProduct();
    }

    public ProductDetailResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductColorDao> productColorDao = productColorRepository.getProductColorById(id);

        List<ProductSizeDao> productSizeDao =  sizeRepository.getProductSizeById(id);

        Category category = categoryRepository.findById(product.getCategoryId()).get();

        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
        productDetailResponseDto.setProductId(product.getId());
        productDetailResponseDto.setCategoryName(category.getName());
        productDetailResponseDto.setName(product.getName());
        productDetailResponseDto.setPrice(product.getPrice());
        productDetailResponseDto.setListColor(productColorDao);
        productDetailResponseDto.setListSize(productSizeDao);
        productDetailResponseDto.setDescription(product.getDescription());
        productDetailResponseDto.setProductCode(product.getProductCode());

        return productDetailResponseDto;
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {

        // oa san pham
        productRepository.deleteById(id);

        // loc ra file anh cu
        List<FileProductProjection> fileOld = fileUrlProduct.filePublicOld(id);

        // xoa thong tin luu truoc do
        // trong product_color
        List<ProductColor> productColors = productColorRepository.findByProductId(id);
        List<Long> productColorIds = productColors.stream()
                .map(ProductColor::getId) // Chuyển mỗi ProductColor thành id của nó
                .collect(Collectors.toList());
        // trong file
        List<FileProductImg> fileProductImgs = fileUrlProduct.findByProductColorIdIn(productColorIds);
        // trong inventory
        List<Inventory> inventories = inventoryRepository.findByProductId(id);

        // xoa thong tin
        productColorRepository.deleteAll(productColors);
        fileUrlProduct.deleteAll(fileProductImgs);
        inventoryRepository.deleteAll(inventories);

        // xoa anh thua tren clound
        fileOld.forEach(img ->{
                try {
                    cloudinaryService.deleteImage(img.getPublicId());
                } catch (Exception e) {
                    System.out.printf(e.getMessage());
                }
        });
    }


    public ProductDetailUpdateDto getProductDetailUpdate(Long id){

        ProductDetailUpdateDto response = new ProductDetailUpdateDto();

       // lấy thông tin chi tiết sản phẩm
        ProductUpdateProjection productUpdateProjection = productRepository
                .getProductUpdateDetails(id);

        // convert data
        response = productMapper.toProductDetailUpdateDto(productUpdateProjection);

        // thong tin mau vaf anh sar pham
        List<ProductColorDetailUpdateDto> responseProductColor = new ArrayList<>();

        List<ProductColorUpdateProjection> productColorUpdateProjection = productColorRepository
                .findProductColorDetailsByProductId(id);
        responseProductColor = productColorMapper.toProductColorDetailUpdateDto(productColorUpdateProjection);

        response.setProductDetail(responseProductColor);

        response.getProductDetail().forEach(item ->{
            // lay thonf tin size vaf quanlity
            List<InventoryUpdateProjection> ResponseInventoryDetailUpdateDto =
                    inventoryRepository.findInventorySizeDetailsByProductId(id, item.getColorId());
            item.setInventory(inventoryMapper.toInventoryDetailUpdateDto(ResponseInventoryDetailUpdateDto));
        });

        return response;
    }

    // Tìm kiếm sản phẩm theo branchId
    public List<ProductWithImageDto> getProductsByBranchId(Long branchId) {
        // Gọi phương thức repository để lấy dữ liệu
        List<Object[]> rawResults = productRepository.findProductsByBranchIdWithImages(branchId);

        // Chuyển đổi dữ liệu từ rawResults sang danh sách DTO ProductWithImage
        List<ProductWithImageDto> products = rawResults.stream()
                .map(row -> new ProductWithImageDto(
                        ((Number) row[0]).longValue(),    // id
                        (String) row[1],                 // name
                        (Double) row[2],                 // price
                        (String) row[3]                  // fileUrl (min)
                ))
                .collect(Collectors.toList());

        return products;
    }

    // Tìm kiếm sản phẩm theo categoryId
    public List<ProductWithImageDto> getProductsByCategoryId(Long categoryId) {
        List<Object[]> rawResults = productRepository.findProductsByCategoryIdWithImages(categoryId);

        return rawResults.stream()
                .map(row -> new ProductWithImageDto(
                        ((Number) row[0]).longValue(),    // ID
                        (String) row[1],                 // Name
                        ((Number) row[2]).doubleValue(), // Price
                        (String) row[3]                  // File URL
                ))
                .collect(Collectors.toList());
    }

    // Tìm kiếm sản phẩm theo branchId hoặc categoryId
    public List<ProductWithImageDto> getProducts(Long branchId, Long categoryId) {
        if (branchId != null) {
            return getProductsByBranchId(branchId); // Trả về danh sách sản phẩm theo branchId
        } else if (categoryId != null) {
            return getProductsByCategoryId(categoryId); // Trả về danh sách sản phẩm theo categoryId
        } else {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu không có tham số nào
        }
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContains(keyword);
    }

}
