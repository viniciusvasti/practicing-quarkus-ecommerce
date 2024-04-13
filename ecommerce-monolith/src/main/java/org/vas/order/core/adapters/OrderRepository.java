package org.vas.order.core.adapters;

import java.util.Optional;
import java.util.Set;

import org.vas.order.core.domain.Order;

public interface OrderRepository {
    Order saveOrder(Order product);
    Optional<Order> findOrderById(Long id);
    Set<Order> findAllOrders();
}
