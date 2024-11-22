package com.example.login.model.service;

import com.example.login.model.entity.Inventory;
import com.example.login.model.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Optional<Long> findInventoryId(Long productId, Long colorId, Long sizeId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        return inventory.map(Inventory::getId);
    }
}