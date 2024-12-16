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
    @Query(value = "SELECT c.id AS id, \n" +
            "       cl.color_name AS colorName, \n" +
            "       s.size_name AS sizeName, \n" +
            "       p.name AS productName, \n" +
            "       p.price AS price, \n" +
            "       c.quantity AS quantity, \n" +
            "       i.id AS inventoryId, \n" +
            "       fpi.file_url AS fileUrl\n" +
            "FROM cart c \n" +
            "JOIN users u ON u.id = c.user_id \n" +
            "JOIN inventory i ON i.id = c.inventory_id\n" +
            "JOIN product_color pc ON pc.product_id = i.product_id AND pc.color_id = i.color_id\n" +
            "JOIN file_product_img fpi ON fpi.product_color_id = pc.id\n" +
            "JOIN product p ON p.id = i.product_id \n" +
            "JOIN color cl ON cl.id = i.color_id \n" +
            "JOIN size s ON s.id = i.size_id \n" +
            "WHERE u.id = :userId\n" +
            "LIMIT 0, 1000", nativeQuery = true)
    List<Object[]> findCartDetailsByUserId(@Param("userId") Long userId);

}