package com.example.login.dto.product;

public interface ProductColorUpdateProjection {

    // Phương thức getter cho id của file

    // Phương thức getter cho id của color
    Long getColorId();

    // Phương thức getter cho file URL
    String getFileUrl();

    // Phương thức getter cho signature
    String getSignature();

    // Phương thức getter cho tên màu (color name)
    String getColorName();  // Thêm getter cho colorName

    // Phương thức getter cho publicId của ảnh
    String getPublicId();  // Thêm getter cho publicId

    String getFileName();
}
