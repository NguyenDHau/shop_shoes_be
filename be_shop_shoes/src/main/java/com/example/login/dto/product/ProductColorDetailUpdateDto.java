package com.example.login.dto.product;

import java.util.List;

public class ProductColorDetailUpdateDto {
    private Long colorId;      // ID của màu sắc
    private String fileUrl;    // URL của hình ảnh
    private String signature;  // Chữ ký của hình ảnh
    private String colorName;  // Tên của màu sắc
    private String publicId;   // Public ID của hình ảnh từ Cloudinary
    private List<InventoryDetailUpdateDto> inventory;
    private String fileName;


    // Getter và Setter cho colorId
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    // Getter và Setter cho fileUrl
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    // Getter và Setter cho signature
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    // Getter và Setter cho colorName
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    // Getter và Setter cho publicId
    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public List<InventoryDetailUpdateDto> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryDetailUpdateDto> inventory) {
        this.inventory = inventory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
