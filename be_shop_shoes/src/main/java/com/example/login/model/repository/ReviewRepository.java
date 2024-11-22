package com.example.login.model.repository;

import com.example.login.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT r.review, u.first_name as firstName, r.start, r.file_img_review as fileImgUrl " +
            "FROM review r " +
            "JOIN users u ON u.id = r.user_id " +
            "JOIN product p ON p.id = r.product_id " +
            "WHERE p.id = :productId", nativeQuery = true)
    List<Object[]> findReviewsByProductId(@Param("productId") Long productId);

    @Query(value = "SELECT p.id, ROUND(AVG(r.start)) AS avgRating, p.name AS productName, " +
            "MIN(fpi.file_url) AS fileUrl, p.price, COUNT(r.id) AS countReview " +
            "FROM review r " +
            "JOIN product p ON p.id = r.product_id " +
            "JOIN product_color pc ON pc.product_id = p.id " +
            "JOIN file_product_img fpi ON fpi.product_color_id = pc.id " +
            "GROUP BY p.id " +
            "ORDER BY avgRating DESC " +
            "LIMIT 6", nativeQuery = true)
    List<Object[]> findTop4ProductsByRating();
}


