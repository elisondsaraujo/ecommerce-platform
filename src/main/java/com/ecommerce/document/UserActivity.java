package com.ecommerce.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "user_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivity {

    @Id
    private String id;

    private Long userId;

    private Integer totalOrders;

    private Integer totalSpent;

    private Integer browsingHistory;

    private List<Activity> activities;

    private LocalDateTime lastActivityAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Activity {
        private String type; // VIEW, PURCHASE, REVIEW, ADD_TO_CART
        private Long productId;
        private LocalDateTime timestamp;
    }
}
