package com.ecommerce.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders_audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderAudit {

    @Id
    private String id;

    private Long orderId;

    private String orderNumber;

    private Long userId;

    private BigDecimal totalAmount;

    private String status;

    private List<StatusChange> statusHistory;

    private LocalDateTime createdAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StatusChange {
        private String previousStatus;
        private String newStatus;
        private LocalDateTime changedAt;
        private String changedBy;
        private String reason;
    }
}
