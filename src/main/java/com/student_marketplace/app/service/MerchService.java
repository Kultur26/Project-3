package com.student_marketplace.app.service;

import com.student_marketplace.app.domain.MerchItem;
import com.student_marketplace.app.repository.MerchItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MerchService {

    private final MerchItemRepository repo;

    public MerchService(MerchItemRepository repo) { this.repo = repo; }

    public List<MerchItem> findAll(String q, String category) {
        if (q != null && !q.isBlank()) {
            return repo.findByNameContainingIgnoreCase(q);
        }
        if (category != null && !category.isBlank() && !"All".equalsIgnoreCase(category)) {
            return repo.findByCategory(category);
        }
        return repo.findAll();
    }

    public MerchItem findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Merch item not found: " + id));
    }

    public MerchItem save(MerchItem item) { return repo.save(item); }
    public void delete(Long id) { repo.deleteById(id); }
}