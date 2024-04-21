package org.vas.payment.infra.persistence;

import org.vas.payment.core.adapters.PaymentRepository;
import org.vas.payment.core.domain.Payment;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentSqlPanacheRepository implements PaymentRepository {
    @Override
    public Payment savePayment(Payment payment) {
        payment.persist();
        return payment;
    }
}
