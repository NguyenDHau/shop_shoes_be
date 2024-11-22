package com.example.login.model.repository;

import com.example.login.model.entity.OrderDetail;
import com.example.login.dto.OrderDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT od.id as orderId, od.quantity, p.name as productName, " +
            "cl.color_name as colorName, s.size_name as sizeName, p.price, MIN(fpi.file_url) AS fileUrl " +
            "FROM order_details od " +
            "JOIN `order` o ON o.id = od.order_id " +
            "JOIN inventory i ON i.id = od.inventory_id " +
            "JOIN product p ON p.id = i.product_id " +
            "JOIN color cl ON cl.id = i.color_id " +
            "JOIN size s ON s.id = i.size_id " +
            "JOIN product_color pc ON pc.product_id = p.id " +
            "JOIN file_product_img fpi ON fpi.product_color_id = pc.id " +
            "WHERE o.id = :orderId " +
            "GROUP BY od.id, od.quantity, p.name, cl.color_name, s.size_name, p.price " +
            "LIMIT 100", nativeQuery = true)
    List<Object[]> findOrderDetailsByOrderId(@Param("orderId") Long orderId);

    List<OrderDetail> findByOrderId(Long orderId);
}
