package com.example.login.model.repository;
import com.example.login.dto.product.InventoryUpdateProjection;
import com.example.login.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductIdAndColorIdAndSizeId(Long productId, Long colorId, Long sizeId);

    @Query(value = "SELECT s.id AS sizeId, s.size_name AS sizeName, s.order AS sizeOrder, " +
            "i.quantity AS quantity, i.color_id AS colorId " +
            "FROM size s " +
            "JOIN inventory i ON s.id = i.size_id " +
            "WHERE i.product_id = :productId and i.color_id = :colorId " +
            "ORDER BY s.order",
          nativeQuery = true)
    List<InventoryUpdateProjection> findInventorySizeDetailsByProductId(@Param("productId") Long productId,
                                                                        @Param("colorId") Long colorId);

    List<Inventory> findByProductId(Long id);
}
