package com.student_marketplace.app.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "merch_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MerchItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private String imageUrl;

    private String category;

    private Integer stock = 0;

    private String sizes;
    private String colors;

    private LocalDateTime createdAt = LocalDateTime.now();
}