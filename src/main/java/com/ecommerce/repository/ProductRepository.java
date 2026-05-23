package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.quantity > 0")
    Page<Product> findActiveProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 AND p.active = true")
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.active = true")
    Page<Product> searchByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.sku = ?1")
    Optional<Product> findBySku(String sku);

    @Query(value = "SELECT * FROM products WHERE active = true ORDER BY sold DESC LIMIT 10", nativeQuery = true)
    List<Product> findTopSelling();
}
