package org.vas.order.presentation.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateOrderItemDTO", description = "Order item identified by SKU and quantity")
public record CreateOrderItemDTO(
        @Schema(description = "Product SKU", required = true) String sku,
        @Schema(description = "Quantity of the product", required = true) int quantity) {

}
