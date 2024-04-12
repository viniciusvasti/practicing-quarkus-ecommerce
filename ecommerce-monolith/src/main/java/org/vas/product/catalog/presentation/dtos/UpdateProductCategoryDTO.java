package org.vas.product.catalog.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UpdateProductCategory", description = "A product category in the catalog")
public record UpdateProductCategoryDTO(
        @Schema(required = true) Long id,
        @Schema(required = true, example = "Category Name") String name) {
}