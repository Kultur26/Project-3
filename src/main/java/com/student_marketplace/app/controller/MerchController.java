package com.student_marketplace.app.controller;

import com.student_marketplace.app.dto.CreateMerchRequest;
import com.student_marketplace.app.dto.MerchItemDto;
import com.student_marketplace.app.factory.MerchItemFactory;
import com.student_marketplace.app.domain.MerchItem;
import com.student_marketplace.app.service.MerchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merch")
public class MerchController {

    private final MerchService service;
    private final MerchItemFactory factory;

    public MerchController(MerchService service, MerchItemFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @GetMapping
    public List<MerchItemDto> all(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category) {
        return service.findAll(q, category).stream()
                .map(factory::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MerchItemDto one(@PathVariable Long id) {
        return factory.toDto(service.findById(id));
    }

    @PostMapping
    public MerchItemDto create(@RequestBody CreateMerchRequest req) {
        MerchItem saved = service.save(factory.fromCreateRequest(req));
        return factory.toDto(saved);
    }

    @PutMapping("/{id}")
    public MerchItemDto update(@PathVariable Long id, @RequestBody CreateMerchRequest req) {
        MerchItem existing = service.findById(id);
        existing.setName(req.getName());
        existing.setDescription(req.getDescription());
        existing.setPrice(req.getPrice());
        existing.setImageUrl(req.getImageUrl());
        existing.setCategory(req.getCategory());
        existing.setStock(req.getStock());
        existing.setSizes(req.getSizes());
        existing.setColors(req.getColors());
        return factory.toDto(service.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}