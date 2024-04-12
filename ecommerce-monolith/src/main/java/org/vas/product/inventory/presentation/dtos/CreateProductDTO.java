package org.vas.product.inventory.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateProductInventory", description = "A product in the inventory holding the stock units")
public record CreateProductDTO(
        @Schema(required = true, example = "12345678") String sku,
        @Schema(required = true, example = "10", description = "The number of units in stock") int stockUnits) {
}