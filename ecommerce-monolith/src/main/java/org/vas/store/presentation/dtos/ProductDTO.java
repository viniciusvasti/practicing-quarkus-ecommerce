package org.vas.store.presentation.dtos;

import java.math.BigDecimal;

public record ProductDTO(Long id, String sku, String name, BigDecimal price, String description, Long categoryId) {
}