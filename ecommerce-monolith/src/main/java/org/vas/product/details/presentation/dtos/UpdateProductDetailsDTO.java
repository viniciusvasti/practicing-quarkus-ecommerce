package org.vas.product.details.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UpdateProduct", description = "A product in the catalog")
public record UpdateProductDetailsDTO(
        @Schema(required = true) Long id,
        @Schema(required = true, example = "Product name") String name,
        @Schema(required = true, example = "Product Description") String description,
        @Schema(required = true, example = "1") Long categoryId) {
}