package org.vas.payment.core.domain;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;
import org.vas.payment.core.adapters.PaymentRepository;
import org.vas.payment.core.ports.PaymentService;
import org.vas.payment.infra.http.PaymentGatewayService;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PaymentServiceImpl implements PaymentService {

    @Inject
    private PaymentRepository paymentRepository;
    @Inject
    private OrderRepository orderRepository;
    @RestClient
    private PaymentGatewayService paymentGatewayService;
    @Inject
    private EventBus eventBus;

    @ConsumeEvent("order.created")
    @Blocking
    public void onOrderCreated(Long orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow();
        chargeOrder(order);
    }

    @Transactional
    public boolean chargeOrder(Order order) {
        Payment payment = new Payment(order.getPaymentAmount(), order);
        Log.debugf("Charging payment: %s", payment);
        var response = paymentGatewayService.charge(payment);
        if (!response.get("status").equals("201")) {
            Log.errorf("Payment failed: %s", response);
            eventBus.publish("order.payment.failed", order.getId());
            return false;
        }
        paymentRepository.savePayment(payment);
        eventBus.publish("order.payment.succeeded", order.getId());
        return true;
    }
}
