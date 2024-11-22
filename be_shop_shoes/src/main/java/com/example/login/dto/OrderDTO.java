package com.example.login.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private String cusName;
    private String cusEmail;
    private String cusPhone;
    private LocalDateTime orderDate;
    private String paymentMethod;
    private Long userId;

    // Constructor, getters, and setters



    public OrderDTO(Long id, String cusName, String cusEmail, String cusPhone, LocalDateTime orderDate, String paymentMethod, Long userId) {
        this.id = id;
        this.cusName = cusName;
        this.cusEmail = cusEmail;
        this.cusPhone = cusPhone;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
