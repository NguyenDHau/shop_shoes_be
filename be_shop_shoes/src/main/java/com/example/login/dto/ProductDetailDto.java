package com.example.login.dto;

import java.util.List;

public class ProductDetailDto {

    private Long colorId;

    private String colorName;

    private String fileUrl;

    private String publicId;

    private String signature;

    private String fileName;

    private List<InventoryDto> inventory;

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<InventoryDto> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryDto> inventory) {
        this.inventory = inventory;
    }
}
