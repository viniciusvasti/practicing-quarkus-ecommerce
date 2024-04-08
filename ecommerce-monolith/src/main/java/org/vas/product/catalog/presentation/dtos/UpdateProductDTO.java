package org.vas.product.catalog.presentation.dtos;

public record UpdateProductDTO(Long id, String name, String description, Long categoryId) {
}