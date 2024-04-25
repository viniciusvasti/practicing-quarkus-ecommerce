package org.vas.order.core.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Schema(name = "OrderedProduct", description = "A product in an order")
@Entity(name = "customer_order_item")
public class OrderedProduct extends PanacheEntity {
    // TODO: set unique constraint
    @Schema(required = true, example = "12345678")
    private String sku;
    @Schema(required = true, example = "3")
    private int quantity;

    public OrderedProduct() {
    }

    public OrderedProduct(String sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
