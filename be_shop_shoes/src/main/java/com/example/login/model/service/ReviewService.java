package com.example.login.model.service;

import com.example.login.dto.ProductRatingDto;
import com.example.login.model.entity.Review;
import com.example.login.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Map<String, Object>> getReviewsByProductId(Long productId) {
        List<Object[]> results = reviewRepository.findReviewsByProductId(productId);
        List<Map<String, Object>> reviews = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("review", result[0]);
            reviewMap.put("firstName", result[1]);
            reviewMap.put("start", result[2]);
            reviewMap.put("fileImgUrl", result[3]);
            reviews.add(reviewMap);
        }

        return reviews;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setProductId(reviewDetails.getProductId());
            review.setUserId(reviewDetails.getUserId());
            review.setReview(reviewDetails.getReview());
            review.setStart(reviewDetails.getStart());
            review.setFileImgReview((reviewDetails.getFileImgReview()));
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<ProductRatingDto> getTop4ProductsByRating() {
        List<Object[]> results = reviewRepository.findTop4ProductsByRating();

        return results.stream().map(result -> new ProductRatingDto(
                ((BigInteger) result[0]).longValue(),           // productId, chuyển từ BigInteger sang Long
                ((Number) result[1]).intValue(),                // avgRating, dùng Number để hỗ trợ cả BigDecimal và Double
                (String) result[2],                             // productName
                (String) result[3],                             // fileUrl
                ((Number) result[4]).doubleValue(),             // price, dùng Number để hỗ trợ cả BigDecimal và Double
                ((BigInteger) result[5]).intValue()               // countReview, chuyển BigInteger thành Integer
        )).collect(Collectors.toList());
    }
}

