package com.example.login.dto.product;

public interface ProductUpdateProjection {

    Long getId();

    Long getCategoryId();

    String getName();

    String getDescription();

    Double getPrice();

    String getProductCode();

    Long getBranchId();

}
