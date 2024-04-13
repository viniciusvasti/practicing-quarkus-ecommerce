package org.vas.order.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.order.core.domain.Order;
import org.vas.order.presentation.dtos.CreateOrderDTO;

public interface OrderService {
    Optional<Order> findById(Long id);
    Set<Order> listAll();
    Order create(CreateOrderDTO orderDto);
}
