package org.vas.store.presentation.dtos;

import java.util.Set;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "StoreProductList", description = "A list of products organized by category to be listed in the store products page")
public record ProductCategoryDTO(
        @Schema(required = true, example = "1") Long id,
        @Schema(required = true, example = "Category Name") String name,
        @Schema(required = true, example = "List of products in this category") Set<ProductDTO> products) {
}