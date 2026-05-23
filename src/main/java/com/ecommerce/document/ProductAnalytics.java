package com.ecommerce.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "product_analytics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAnalytics {

    @Id
    private String id;

    private Long productId;

    private Integer totalViews;

    private Integer totalSold;

    private Double averageRating;

    private Integer reviewCount;

    private List<DailyMetric> dailyMetrics;

    private LocalDateTime lastUpdated;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DailyMetric {
        private String date;
        private Integer views;
        private Integer sales;
        private Double revenue;
    }
}
