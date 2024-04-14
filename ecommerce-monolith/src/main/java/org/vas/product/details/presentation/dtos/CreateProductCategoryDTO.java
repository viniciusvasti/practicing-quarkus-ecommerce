package org.vas.product.details.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateProductCategory", description = "A product category in the catalog")
public record CreateProductCategoryDTO(
        @Schema(required = true, example = "Category Name") String name) {
}