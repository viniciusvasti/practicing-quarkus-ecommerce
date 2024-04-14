package org.vas.product.pricing.presentation.dtos;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UpdateProductPrice", description = "A product's price by SKU")
public record UpdateProductPriceDTO(@Schema(required = true, example = "12345678") Long id,
        @Schema(required = true, example = "100.97") BigDecimal price) {
}