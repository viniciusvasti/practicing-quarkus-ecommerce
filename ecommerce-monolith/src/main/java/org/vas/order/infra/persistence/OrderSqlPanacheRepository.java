package org.vas.order.infra.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderSqlPanacheRepository implements OrderRepository {

    @Override
    public Order saveOrder(Order order) {
        order.persist();
        return order;
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Order.findByIdOptional(id);
    }

    @Override
    public Set<Order> findAllOrders() {
        List<Order> orders = Order.listAll();
        // LinkedHashSet to keep the order of list elements
        return new HashSet<Order>(orders);
    }

}
