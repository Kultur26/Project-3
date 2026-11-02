package com.student_marketplace.app.dto;

import lombok.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateMerchRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String category;
    private Integer stock;
    private String sizes;
    private String colors;
}