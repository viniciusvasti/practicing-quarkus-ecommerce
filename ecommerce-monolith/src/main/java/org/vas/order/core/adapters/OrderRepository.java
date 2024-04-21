package org.vas.order.core.adapters;

import java.util.Optional;
import java.util.Set;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.OrderStatus;

public interface OrderRepository {
    Order saveOrder(Order order);
    Optional<Order> findOrderById(Long id);
    Set<Order> findAllOrders();
    void updateOrderStatus(Long id, OrderStatus status);
}
