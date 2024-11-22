package com.example.login.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="inventory_id")
    private Long inventoryId;

    @Column(name="user_id")
    private Long userId;

    private int quantity;

    @Column(name="time_create")
    private Instant timeCreate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

}