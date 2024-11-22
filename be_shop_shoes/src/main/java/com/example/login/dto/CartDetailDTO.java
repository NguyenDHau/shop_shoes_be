package com.example.login.dto;

public class CartDetailDTO {
    private Long id;
    private String colorName;
    private String sizeName;
    private String productName;
    private Double price;
    private Integer quantity;
    private Long inventoryId;

    private String fileUrl;

    // Constructors

    public CartDetailDTO(Long id, String colorName, String sizeName, String productName, Double price, Integer quantity, Long inventoryId, String fileUrl) {
        this.id = id;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // Getters và Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }

    public String getSizeName() { return sizeName; }
    public void setSizeName(String sizeName) { this.sizeName = sizeName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
