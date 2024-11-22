package com.example.login.dto;

public class OrderRequest {
    private String paymentMethod;
    private String shippingAddress;
    private String cusName;
    private String cusPhone;
    private String cusEmail;
    private String orderNote;

    private Long userId;
    private Double toTal;

    // Getter và Setter
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getCusName() { return cusName; }
    public void setCusName(String cusName) { this.cusName = cusName; }

    public String getCusPhone() { return cusPhone; }
    public void setCusPhone(String cusPhone) { this.cusPhone = cusPhone; }

    public String getCusEmail() { return cusEmail; }
    public void setCusEmail(String cusEmail) { this.cusEmail = cusEmail; }

    public String getOrderNote() { return orderNote; }
    public void setOrderNote(String orderNote) { this.orderNote = orderNote; }

    public Double getToTal() {
        return toTal;
    }

    public void setToTal(Double toTal) {
        this.toTal = toTal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
