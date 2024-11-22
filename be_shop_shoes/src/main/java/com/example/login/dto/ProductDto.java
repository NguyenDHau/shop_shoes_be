package com.example.login.dto;

import java.util.List;

public class ProductDto {

    private Long id;
    private Long categoryId; // Chỉ cần ID
    private String name;
    private String description;
    private Double price;
    private String productCode;
    private Long branchId;

    private List<ProductDetailDto> productDetail;

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

    public List<ProductDetailDto> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailDto> productDetail) {
        this.productDetail = productDetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
