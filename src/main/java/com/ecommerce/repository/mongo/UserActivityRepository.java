package com.ecommerce.repository.mongo;

import com.ecommerce.document.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActivityRepository extends MongoRepository<UserActivity, String> {

    Optional<UserActivity> findByUserId(Long userId);
}
