package com.student_marketplace.app.repository;

import com.student_marketplace.app.domain.MarketplaceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketplaceItemRepository extends JpaRepository<MarketplaceItem, Long> {

    List<MarketplaceItem> findByTitleContainingIgnoreCase(String title);

    List<MarketplaceItem> findByCategory(String category);
}
