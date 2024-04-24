package org.vas.notification.core.domain;

import org.vas.notification.core.ports.NotificationService;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyByEmail(String message) {
        Log.infof("Sending email notification: %s", message);
    }

    @ConsumeEvent("order.created")
    public void onOrderCreated(Long orderId) {
        notifyByEmail("Your order " + orderId + " created");
    }

    @ConsumeEvent("order.payment.succeeded")
    public void onOrderPaymentSucceeded(Long orderId) {
        notifyByEmail("Your order " + orderId + " payment succeeded");
    }

    @ConsumeEvent("order.payment.failed")
    public void onOrderPaymentFailed(Long orderId) {
        notifyByEmail("Your order " + orderId + " payment failed");
    }

    @ConsumeEvent("order.shipping.succeeded")
    public void onOrderShippingRequestSucceeded(Long orderId) {
        notifyByEmail("Your order " + orderId + " shipping is in progress");
    }
}
