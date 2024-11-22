package com.example.login.dto;

public class MoMoCallbackResponse {

    private String partnerCode;    // Mã đối tác MoMo
    private String accessKey;      // Access key của MoMo
    private String requestId;      // ID yêu cầu
    private String orderId;        // ID đơn hàng
    private String amount;         // Số tiền thanh toán
    private String transId;        // Mã giao dịch MoMo
    private String message;        // Thông điệp trả về từ MoMo
    private String resultCode;     // Mã kết quả thanh toán (0 là thành công)
    private String errorCode;      // Mã lỗi (0 là không có lỗi)
    private String signature;      // Chữ ký mã hóa dùng để xác thực

    // Getters và Setters
    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}

