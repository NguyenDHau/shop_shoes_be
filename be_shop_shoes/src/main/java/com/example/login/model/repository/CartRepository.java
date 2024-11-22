package com.example.login.model.repository;

import com.example.login.model.entity.Cart;
import com.example.login.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    void deleteByUserId(Long userId);

    // Tìm Cart dựa trên userId và inventoryId
    Optional<Cart> findByUserIdAndInventoryId(Long userId, Long inventoryId);

    // Lấy chi tiết giỏ hàng bao gồm tên màu, tên kích thước, tên sản phẩm và giá
    @Query(value = "SELECT c.id as id, " +
            "cl.color_name as colorName, " +
            "s.size_name as sizeName, " +
            "p.name as productName, " +
            "p.price as price, " +
            "c.quantity as quantity, " +
            "i.id as inventoryId, " +
            "MIN(fpi.file_url) as fileUrl " + // Lấy một URL duy nhất cho mỗi sản phẩm
            "FROM cart c " +
            "JOIN users u ON u.id = c.user_id " +
            "JOIN inventory i ON i.id = c.inventory_id " +
            "JOIN product p ON p.id = i.product_id " +
            "JOIN color cl ON cl.id = i.color_id " +
            "JOIN size s ON s.id = i.size_id " +
            "JOIN product_color pc ON pc.product_id = p.id " +
            "JOIN file_product_img fpi ON fpi.product_color_id = pc.id " +
            "WHERE u.id = :userId " +
            "GROUP BY c.id, cl.color_name, s.size_name, p.name, p.price, c.quantity, i.id " +
            "LIMIT 0, 1000", nativeQuery = true)
    List<Object[]> findCartDetailsByUserId(@Param("userId") Long userId);

}