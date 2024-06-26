package org.vas.order.core.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.exceptions.WrongPaymentAmountException;
import org.vas.order.core.ports.OrderService;
import org.vas.order.presentation.dtos.CreateOrderDTO;
import org.vas.product.inventory.core.ports.ProductInventoryService;
import org.vas.product.pricing.core.domain.ProductPrice;
import org.vas.product.pricing.core.ports.ProductPriceService;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private ProductInventoryService inventoryService;
    @Inject
    private ProductPriceService priceService;
    @Inject
    private EventBus eventBus;

    public Optional<Order> findById(Long id) {
        return orderRepository.findOrderById(id);
    }

    public Set<Order> listAll() {
        return orderRepository.findAllOrders();
    }

    public Order requestOrder(CreateOrderDTO orderDto) {
        Log.tracef("Creating a new order: %s", orderDto);
        Order order = mapToOrder(orderDto);
        Order createdOrder = create(order);

        eventBus.publish("order.created", createdOrder.id);
        return createdOrder;
    }

    @Transactional
    public Order create(Order order) {
        order.setStatus(OrderStatus.PENDING);
        if (!order.isValid()) {
            Log.warnf("Attempt to create invalid order: %s", order);
            throw new IllegalArgumentException("Invalid order");
        }
        if (!orderPaymentAmountMatches(order)) {
            throw new WrongPaymentAmountException();
        }

        inventoryService.decreaseOrderStockUnits(order);
        order.setStatus(OrderStatus.CONFIRMED);

        return orderRepository.saveOrder(order);
    }

    private Order mapToOrder(CreateOrderDTO orderDto) {
        List<OrderedProduct> items = orderDto.items().stream()
                .map(item -> new OrderedProduct(item.sku(), item.quantity())).toList();
        return new Order(items, orderDto.paymentAmount());
    }

    /**
     * Makes sure the payment amount in the order matches the total amount of the items included in
     * the order
     * 
     * @param order
     * @return boolean
     */
    private boolean orderPaymentAmountMatches(Order order) {
        List<String> skus = order.getItems().stream().map(OrderedProduct::getSku).toList();
        Map<String, ProductPrice> prices = priceService.findBySkus(skus).stream()
                .collect(Collectors.toMap(ProductPrice::getSku, Function.identity()));
        BigDecimal total = order.getItems().stream()
                .map(item -> prices.get(item.getSku()).getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        boolean itMatches = total.compareTo(order.getPaymentAmount()) == 0;
        if (!itMatches) {
            Log.warnf("Payment amount does not match the total amount of the order: %s, %s", order,
                    total);
        }
        return itMatches;
    }

    @ConsumeEvent("order.payment.succeeded")
    @Blocking
    @Transactional
    public void onOrderPaymentSucceeded(Long orderId) {
        Log.infof("Listening to order payment succeeded: %s", orderId);
        orderRepository.updateOrderStatus(orderId, OrderStatus.PAID);
    }

    @ConsumeEvent("order.payment.failed")
    @Blocking
    @Transactional
    public void onOrderPaymentFailed(Long orderId) {
        Log.infof("Listening to order payment failed: %s", orderId);
        orderRepository.updateOrderStatus(orderId, OrderStatus.PAYMENT_FAILED);
    }

    @ConsumeEvent("order.shipping.succeeded")
    @Blocking
    @Transactional
    public void onOrderShippingRequestSucceeded(Long orderId) {
        Log.infof("Listening to order shipping succeeded: %s", orderId);
        orderRepository.updateOrderStatus(orderId, OrderStatus.SHIPPED);
    }

    @ConsumeEvent("order.shipping.failed")
    @Blocking
    @Transactional
    public void onOrderShippingRequestFailed(Long orderId) {
        Log.infof("Listening to order shipping failed: %s", orderId);
        orderRepository.updateOrderStatus(orderId, OrderStatus.SHIPPING_FAILED);
    }
}
