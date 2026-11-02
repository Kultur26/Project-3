package com.student_marketplace.app.repository;

import com.student_marketplace.app.domain.MerchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MerchItemRepository extends JpaRepository<MerchItem, Long> {
    List<MerchItem> findByCategory(String category);
    List<MerchItem> findByNameContainingIgnoreCase(String q);
}