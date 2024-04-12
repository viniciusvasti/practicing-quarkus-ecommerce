package org.vas.product.pricing.presentation.dtos;

import java.math.BigDecimal;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateProductPrice", description = "A product's price by SKU")
public record CreateProductDTO(@Schema(required = true, example = "12345678") String sku,
        @Schema(required = true, example = "100.97") BigDecimal price) {
}