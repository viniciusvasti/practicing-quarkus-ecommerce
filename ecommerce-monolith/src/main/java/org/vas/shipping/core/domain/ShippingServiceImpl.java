package org.vas.shipping.core.domain;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.vas.notification.core.ports.NotificationService;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.OrderStatus;
import org.vas.shipping.core.ports.ShippingService;
import org.vas.shipping.infra.http.ShippingGatewayService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShippingServiceImpl implements ShippingService {

    @Inject
    private OrderRepository orderRepository;
    @RestClient
    private ShippingGatewayService shippingGatewayService;
    @Inject
    private NotificationService notificationService;

    @Transactional
    public void shipOrder(Order order) {
        Log.debugf("Shipping order: %s", order);
        var response = shippingGatewayService.ship(order);
        if (!response.get("status").equals("201")) {
            Log.errorf("Shipping request failed: %s", response);
            orderRepository.updateOrderStatus(order.getId(), OrderStatus.SHIPPING_FAILED);
            return;
        }
        notificationService.notifyByEmail("Your order " + order.getId() + " has been shipped");
        orderRepository.updateOrderStatus(order.getId(), OrderStatus.SHIPPED);
    }
}
