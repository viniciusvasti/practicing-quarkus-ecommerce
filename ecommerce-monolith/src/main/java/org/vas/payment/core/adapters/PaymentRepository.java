package org.vas.payment.core.adapters;

import org.vas.payment.core.domain.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
