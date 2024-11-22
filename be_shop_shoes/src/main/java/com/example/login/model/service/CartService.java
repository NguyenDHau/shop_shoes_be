package com.example.login.model.service;

import com.example.login.dto.CartDetailDTO;
import com.example.login.model.entity.Cart;
import com.example.login.model.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Tạo mới hoặc cập nhật cart
    public Cart createOrUpdateCart(Cart cart) {
        // Kiểm tra nếu đã tồn tại cart với userId và inventoryId
        Optional<Cart> existingCart = cartRepository.findByUserIdAndInventoryId(cart.getUserId(), cart.getInventoryId());

        if (existingCart.isPresent()) {
            // Nếu tồn tại, cập nhật số lượng
            Cart existingCartItem = existingCart.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cart.getQuantity());
            return cartRepository.save(existingCartItem);
        } else {
            // Nếu chưa tồn tại, tạo mới cart
            cart.setTimeCreate(Instant.now());
            return cartRepository.save(cart);
        }
    }

    // Lấy thông tin tất cả các cart
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Lấy thông tin một cart theo ID
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    // Cập nhật một cart
    public Cart updateCart(Long id, Cart cartDetails) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart không tồn tại với id: " + id));

        cart.setInventoryId(cartDetails.getInventoryId());
        cart.setUserId(cartDetails.getUserId());
        cart.setQuantity(cartDetails.getQuantity());

        return cartRepository.save(cart);
    }

    // Xóa một cart
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    // Phương thức lấy chi tiết giỏ hàng
    public List<CartDetailDTO> getCartDetailsByUserId(Long userId) {
        List<Object[]> results = cartRepository.findCartDetailsByUserId(userId);

        // Chuyển đổi kết quả từ Object[] thành CartDetailDTO
        return results.stream().map(row -> new CartDetailDTO(
                row[0] instanceof BigInteger ? ((BigInteger) row[0]).longValue() : (Long) row[0], // id
                (String) row[1],      // colorName
                (String) row[2],      // sizeName
                (String) row[3],      // productName
                (Double) row[4],      // price
                (Integer) row[5],      // quantity
                row[6] instanceof BigInteger ? ((BigInteger) row[6]).longValue() : (Long) row[6], // inventoryId
                (String) row[7]
        )).collect(Collectors.toList());
    }
}
