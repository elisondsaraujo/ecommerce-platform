package com.ecommerce.repository.mongo;

import com.ecommerce.document.OrderAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderAuditRepository extends MongoRepository<OrderAudit, String> {

    Optional<OrderAudit> findByOrderId(Long orderId);

    @Query("{ 'userId': ?0 }")
    List<OrderAudit> findByUserId(Long userId);

    @Query("{ 'status': ?0 }")
    List<OrderAudit> findByStatus(String status);
}
