package com.example.login.controller;

import com.example.login.model.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // API để tìm kiếm inventoryId bằng productId, colorId và sizeId
    @GetMapping("/search")
    public ResponseEntity<Long> searchInventoryId(
            @RequestParam Long productId,
            @RequestParam Long colorId,
            @RequestParam Long sizeId) {

        Optional<Long> inventoryId = inventoryService.findInventoryId(productId, colorId, sizeId);

        return inventoryId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}