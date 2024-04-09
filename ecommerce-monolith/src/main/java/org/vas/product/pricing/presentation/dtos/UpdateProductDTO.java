package org.vas.product.pricing.presentation.dtos;

import java.math.BigDecimal;

public record UpdateProductDTO(Long id, BigDecimal price) {
}