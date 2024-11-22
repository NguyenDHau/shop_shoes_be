package com.example.login.model.repository;

import com.example.login.Dao.ProductSizeDao;
import com.example.login.model.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query(value = "SELECT DISTINCT s.id AS 'id',\n" +
            "                s.size_name AS 'sizeName',\n" +
            "                s.order,\n" +
            "                i.quantity,\n" +
            "                i.color_id AS 'colorId'\n" +
            "FROM size s\n" +
            "JOIN inventory i ON s.id = i.size_id\n" +
            "WHERE i.product_id = :productId\n" +
            "ORDER BY s.order", nativeQuery = true)
    List<ProductSizeDao> getProductSizeById(@Param("productId") Long productId);
}
