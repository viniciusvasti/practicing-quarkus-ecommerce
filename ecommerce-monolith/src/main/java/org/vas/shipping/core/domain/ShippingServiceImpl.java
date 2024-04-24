package org.vas.shipping.core.domain;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;
import org.vas.shipping.core.ports.ShippingService;
import org.vas.shipping.infra.http.ShippingGatewayService;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.eventbus.EventBus;
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
    private EventBus eventBus;

    @ConsumeEvent("order.payment.succeeded")
    @Blocking
    public void onOrderPaymentSucceeded(Long orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow();
        shipOrder(order);
    }

    @Transactional
    public void shipOrder(Order order) {
        Log.debugf("Shipping order: %s", order);
        var response = shippingGatewayService.ship(order);
        if (!response.get("status").equals("201")) {
            Log.errorf("Shipping request failed: %s", response);
            eventBus.publish("order.shipping.failed", order.getId());
            return;
        }
        eventBus.publish("order.shipping.succeeded", order.getId());
    }
}
