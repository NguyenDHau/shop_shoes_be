package com.example.login.dto.product;

import java.util.List;

public class ProductDetailUpdateDto {
    private Long id;
    private Long categoryId;
    private Long branchId;
    private String name;
    private String description;
    private Double price;
    private String productCode;
    private List<ProductColorDetailUpdateDto> productDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<ProductColorDetailUpdateDto> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductColorDetailUpdateDto> productDetail) {
        this.productDetail = productDetail;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
