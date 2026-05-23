package com.ecommerce.repository;

import com.ecommerce.model.InventoryLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

    @Query("SELECT il FROM InventoryLog il WHERE il.product.id = ?1 ORDER BY il.createdAt DESC")
    Page<InventoryLog> findByProductId(Long productId, Pageable pageable);

    @Query("SELECT il FROM InventoryLog il WHERE il.action = ?1 ORDER BY il.createdAt DESC")
    Page<InventoryLog> findByAction(InventoryLog.InventoryAction action, Pageable pageable);

    @Query("SELECT il FROM InventoryLog il WHERE il.createdAt BETWEEN ?1 AND ?2 ORDER BY il.createdAt DESC")
    Page<InventoryLog> findByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
