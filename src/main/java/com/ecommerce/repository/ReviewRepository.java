package com.ecommerce.repository;

import com.ecommerce.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product.id = ?1 ORDER BY r.createdAt DESC")
    Page<Review> findByProductId(Long productId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user.id = ?1 ORDER BY r.createdAt DESC")
    Page<Review> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = ?1")
    Double getAverageRatingByProduct(Long productId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.product.id = ?1")
    Long getReviewCountByProduct(Long productId);
}
