package org.vas.product.inventory.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "UpdateProductInventory", description = "A product in the inventory holding the stock units")
public record UpdateProductInventoryDTO(@Schema(required = true) Long id,
        @Schema(required = true, example = "10", description = "The number of units in stock") int stockUnits) {
}