package com.example.login.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private Long orderId;
    private Integer quantity;
    private String productName;
    private String colorName;
    private String sizeName;
    private Double price;
    private String fileUrl;

    public OrderDetailDTO(Long orderId, Integer quantity, String productName, String colorName, String sizeName, Double price, String fileUrl) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.productName = productName;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.price = price;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
