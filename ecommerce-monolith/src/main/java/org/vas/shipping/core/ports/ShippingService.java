package org.vas.shipping.core.ports;

import org.vas.order.core.domain.Order;

public interface ShippingService {
    void shipOrder(Order order);
}
