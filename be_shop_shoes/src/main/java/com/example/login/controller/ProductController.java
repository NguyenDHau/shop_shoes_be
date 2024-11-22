package com.example.login.controller;

import com.example.login.dto.ProductDetailResponseDto;
import com.example.login.dto.ProductDto;
import com.example.login.dto.ProductResponse;
import com.example.login.dto.product.ProductDetailUpdateDto;
import com.example.login.model.entity.Product;
import com.example.login.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details-update/{id}")
    public ResponseEntity<ProductDetailUpdateDto> getProductDetailUpdate(@PathVariable Long id) {
        ProductDetailUpdateDto productDetailUpdateDto = productService.getProductDetailUpdate(id);
        return ResponseEntity.ok(productDetailUpdateDto);
    }
    
}