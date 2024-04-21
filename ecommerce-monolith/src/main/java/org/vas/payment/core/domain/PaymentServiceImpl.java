package org.vas.payment.core.domain;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.vas.order.core.adapters.OrderRepository;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.OrderStatus;
import org.vas.payment.core.adapters.PaymentRepository;
import org.vas.payment.core.ports.PaymentService;
import org.vas.payment.infra.http.PaymentGatewayService;
import io.quarkus.logging.Log;
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

    // TODO: return a boolean to indicate if the payment was successful
    @Transactional
    public void chargeOrder(Order order) {
        Payment payment = new Payment(order.getPaymentAmount(), order);
        Log.debugf("Charging payment: %s", payment);
        var response = paymentGatewayService.charge(payment);
        if (!response.get("status").equals("201")) {
            Log.errorf("Payment failed: %s", response);
            orderRepository.updateOrderStatus(order.getId(), OrderStatus.PAYMENT_FAILED);
            return;
        }
        paymentRepository.savePayment(payment);
        orderRepository.updateOrderStatus(order.getId(), OrderStatus.PAID);
    }
}