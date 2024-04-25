package org.vas.payment.core.domain;

import java.math.BigDecimal;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.vas.order.core.domain.Order;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Schema(name = "Payment", description = "A payment in the store")
@Entity
public class Payment extends PanacheEntity {
    @Schema(required = true, description = "Payment amount")
    private BigDecimal amount;
    @OneToOne(targetEntity = Order.class, optional = false)
    @Schema(required = true, description = "Order associated with the payment")
    private Order order;

    public Payment() {}

    public Payment(BigDecimal amount, Order order) {
        this.amount = amount;
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isValid() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
