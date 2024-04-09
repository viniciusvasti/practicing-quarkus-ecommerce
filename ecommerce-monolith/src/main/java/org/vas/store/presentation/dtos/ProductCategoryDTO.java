package org.vas.store.presentation.dtos;

import java.util.Set;

public record ProductCategoryDTO(Long id, String name, Set<ProductDTO> products) {
}