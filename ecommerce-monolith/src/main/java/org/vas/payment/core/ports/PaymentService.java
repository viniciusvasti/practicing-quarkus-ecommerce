package org.vas.payment.core.ports;

import org.vas.order.core.domain.Order;

public interface PaymentService {
    boolean chargeOrder(Order order);
}
