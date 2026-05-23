package com.ecommerce.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    private String id;

    private Long userId;

    private String type; // ORDER_CONFIRMED, SHIPMENT_TRACKING, REVIEW_REQUEST, PROMOTION

    private String title;

    private String message;

    private Boolean read = false;

    private String relatedEntityId;

    private String relatedEntityType;

    private LocalDateTime createdAt;

    private LocalDateTime readAt;
}
