package com.example.login.model.repository;

import com.example.login.dto.ProductResponse;
import com.example.login.dto.product.ProductUpdateProjection;
import com.example.login.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);

    @Query(value = "select p.id,\n" +
            "             file_img.file_url as 'fileUrl',\n" +
            "             p.name,\n" +
            "             p.price,\n" +
            "             total_color.total_color as 'totalColor',\n" +
            "             c.name as 'categoryName',\n" +
            "             p.description,\n" +
            "             p.product_code\n" +
            "            from product p\n" +
            "            join (\n" +
            "            WITH RankedImages AS  (\n" +
            "\t\t\t\tSELECT \n" +
            "\t\t\t\t\tpc.product_id, \n" +
            "\t\t\t\t\tfpi.file_url,\n" +
            "\t\t\t\t\tROW_NUMBER() OVER (PARTITION BY pc.product_id ORDER BY fpi.order) AS rn\n" +
            "\t\t\t\tFROM \n" +
            "\t\t\t\t\tproduct_color pc\n" +
            "\t\t\t\tJOIN \n" +
            "\t\t\t\t\tfile_product_img fpi ON pc.id = fpi.product_color_id\n" +
            "\t\t\t)\n" +
            "\t\t\tSELECT \n" +
            "\t\t\t\tproduct_id, \n" +
            "\t\t\t\tfile_url\n" +
            "\t\t\tFROM \n" +
            "\t\t\t\tRankedImages\n" +
            "\t\t\tWHERE \n" +
            "\t\t\t\trn = 1\n" +
            "            ) as file_img on file_img.product_id = p.id\n" +
            "            join (\n" +
            "            select count(pc.color_id) as 'total_color', pc.product_id\n" +
            "            from product_color pc\n" +
            "            group by pc.product_id\n" +
            "            ) as total_color on total_color.product_id = p.id\n" +
            "            join category c on c.id = p.category_id;", nativeQuery = true)
    List<ProductResponse> getAllProduct();

    @Query(value = "select \n" +
            "p.id,\n" +
            "p.category_id as 'categoryId',\n" +
            "p.branch_id as 'branchId',\n" +
            "p.name,\n" +
            "p.description,\n" +
            "p.price,\n" +
            "p.product_code as productCode\n" +
            "from product p\n" +
            "where p.id = :id ", nativeQuery = true)
    ProductUpdateProjection getProductUpdateDetails(@Param("id") Long id);
}