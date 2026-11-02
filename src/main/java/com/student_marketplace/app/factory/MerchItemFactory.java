package com.student_marketplace.app.factory;

import com.student_marketplace.app.dto.CreateMerchRequest;
import com.student_marketplace.app.dto.MerchItemDto;
import com.student_marketplace.app.domain.MerchItem;
import org.springframework.stereotype.Component;

@Component
public class MerchItemFactory {

    public MerchItemDto toDto(MerchItem e) {
        if (e == null) return null;
        return MerchItemDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .price(e.getPrice())
                .imageUrl(e.getImageUrl())
                .category(e.getCategory())
                .stock(e.getStock())
                .sizes(e.getSizes())
                .colors(e.getColors())
                .createdAt(e.getCreatedAt())
                .build();
    }

    public MerchItem fromCreateRequest(CreateMerchRequest r) {
        if (r == null) return null;
        return MerchItem.builder()
                .name(r.getName())
                .description(r.getDescription())
                .price(r.getPrice())
                .imageUrl(r.getImageUrl())
                .category(r.getCategory())
                .stock(r.getStock())
                .sizes(r.getSizes())
                .colors(r.getColors())
                .build();
    }
}