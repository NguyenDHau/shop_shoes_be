package com.example.login.dto;

public class ProductRatingDto {
    private Long productId;
    private Integer avgRating;
    private String productName;
    private String fileUrl;
    private Double price;
    private Integer countReview;

    public ProductRatingDto(Long productId, Integer avgRating, String productName, String fileUrl, Double price, Integer countReview) {
        this.productId = productId;
        this.avgRating = avgRating;
        this.productName = productName;
        this.fileUrl = fileUrl;
        this.price = price;
        this.countReview = countReview;
    }

    // Getters and setters for each property


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCountReview() {
        return countReview;
    }

    public void setCountReview(Integer countReview) {
        this.countReview = countReview;
    }
}