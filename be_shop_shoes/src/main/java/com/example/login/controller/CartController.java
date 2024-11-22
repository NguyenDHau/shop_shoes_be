package com.example.login.controller;
import com.example.login.dto.CartDetailDTO;
import com.example.login.model.entity.Cart;
import com.example.login.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Tạo mới một cart
    @PostMapping
    public Cart createOrUpdateCart(@RequestBody Cart cart) {
        return cartService.createOrUpdateCart(cart);
    }

    // Lấy thông tin tất cả các cart
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    // Lấy thông tin một cart theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cập nhật một cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        try {
            Cart updatedCart = cartService.updateCart(id, cartDetails);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa một cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<List<CartDetailDTO>> getCartDetails(@PathVariable Long userId) {
        List<CartDetailDTO> cartDetails = cartService.getCartDetailsByUserId(userId);
        return ResponseEntity.ok(cartDetails);
    }
}