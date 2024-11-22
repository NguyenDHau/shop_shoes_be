package com.example.login.dto;

public class RevenueDTO {
    private Double sale;
    private Long countOrder;

    public RevenueDTO(Double sale, Long countOrder) {
        this.sale = sale;
        this.countOrder = countOrder;
    }

    // Getters và setters
    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Long getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(Long countOrder) {
        this.countOrder = countOrder;
    }
}

