package com.example.login.dto;

public class ProductWithImageDto {
    private Long id;
    private String name;
    private Double price;
    private String fileUrl;

    public ProductWithImageDto(Long id, String name, Double price, String fileUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
