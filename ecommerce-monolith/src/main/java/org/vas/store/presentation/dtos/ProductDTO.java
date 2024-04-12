package org.vas.store.presentation.dtos;

import java.math.BigDecimal;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "StoreProduct", description = "A product to be listed in the store products page")
public record ProductDTO(
        @Schema(required = true, example = "1") Long id,
        @Schema(required = true, example = "12345678") String sku,
        @Schema(required = true, example = "Product Name") String name,
        @Schema(required = true, example = "9.99") BigDecimal price,
        @Schema(required = true, example = "Product Description") String description,
        @Schema(required = true, example = "1") Long categoryId) {
}