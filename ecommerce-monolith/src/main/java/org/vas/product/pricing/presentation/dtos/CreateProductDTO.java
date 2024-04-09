package org.vas.product.pricing.presentation.dtos;

import java.math.BigDecimal;

public record CreateProductDTO(String sku, BigDecimal price) {
}