package com.student_marketplace.app.service;

import com.student_marketplace.app.domain.MarketplaceItem;
import com.student_marketplace.app.repository.MarketplaceItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarketItemService {

    private final MarketplaceItemRepository repo;

    public MarketItemService(MarketplaceItemRepository repo) { this.repo = repo; }

    public List<MarketplaceItem> findAll(String q, String category) {
        if (q != null && !q.isBlank()) {
            return repo.findByTitleContainingIgnoreCase(q);
        }
        if (category != null && !category.isBlank() && !"All".equalsIgnoreCase(category)) {
            return repo.findByCategory(category);
        }
        return repo.findAll();
    }

    public MarketplaceItem findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Marketplace item not found: " + id));
    }

    public MarketplaceItem save(MarketplaceItem item) { return repo.save(item); }
    public void delete(Long id) { repo.deleteById(id); }
}
