package com.ecommerce.repository.mongo;

import com.ecommerce.document.ProductAnalytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductAnalyticsRepository extends MongoRepository<ProductAnalytics, String> {

    Optional<ProductAnalytics> findByProductId(Long productId);

    @Query("{ 'totalSold': { $gt: ?0 } }")
    java.util.List<ProductAnalytics> findBestSellers(Integer minSales);
}
