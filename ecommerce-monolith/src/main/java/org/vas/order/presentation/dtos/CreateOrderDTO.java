package org.vas.order.presentation.dtos;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateOrderDTO", description = "Order creation data")
public record CreateOrderDTO(
        @Schema(description = "Customer name", required = true) String customerName,
        @Schema(description = "Customer email", required = true) String customerEmail,
        @Schema(description = "Shipping address", required = true) String shippingAddress,
        @Schema(description = "Payment amount", required = true) BigDecimal paymentAmount,
        @Schema(description = "Order items", required = true) List<CreateOrderItemDTO> items) {

}
