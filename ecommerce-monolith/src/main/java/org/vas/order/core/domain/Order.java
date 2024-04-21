package org.vas.order.core.domain;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Schema(name = "Order", description = "An order in the store")
@Entity(name = "customer_order")
public class Order extends PanacheEntity {
    @Schema(required = true, description = "Order status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Schema(required = true, description = "Payment amount for the order")
    private BigDecimal paymentAmount;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = OrderedProduct.class, fetch = FetchType.LAZY, orphanRemoval = true)
    // NOTE: the children insert statement were having null values for the customer_order_id column. It was fixed by adding the nullable = false attribute
    @JoinColumn(name = "customer_order_id", nullable = false)
    @Schema(required = true)
    private List<OrderedProduct> items = List.of();

    public Order() {
    }

    public Order(List<OrderedProduct> items, BigDecimal paymentAmount) {
        this.items = items;
        this.paymentAmount = paymentAmount;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public List<OrderedProduct> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public boolean isValid() {
        return status != null;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", status=" + status + ", paymentAmount=" + paymentAmount + ", items=" + items + "]";
    }
}
