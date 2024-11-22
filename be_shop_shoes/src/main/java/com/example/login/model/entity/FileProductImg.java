package com.example.login.model.entity;

import javax.persistence.*;

@Entity
public class FileProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="file_url")
    private String fileUrl;
    @Column(name="order")
    private Long order;

    @Column(name="product_color_id")
    private Long productColorId;

    @Column(name="public_id")
    private String publicId;

    @Column(name="signature")
    private String signature;

    @Column(name="file_name")
    private String fileName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }


    public Long getProductColorId() {
        return productColorId;
    }

    public void setProductColorId(Long productColorId) {
        this.productColorId = productColorId;
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
}
