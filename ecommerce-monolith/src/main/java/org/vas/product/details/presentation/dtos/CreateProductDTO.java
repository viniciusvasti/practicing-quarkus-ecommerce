package org.vas.product.details.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateProduct", description = "A product in the catalog")
public record CreateProductDTO(
        @Schema(required = true, example = "12345678") String sku,
        @Schema(required = true, example = "Product Name") String name,
        @Schema(required = true, example = "Product Description") String description,
        @Schema(required = true, example = "1") Long categoryId) {
}