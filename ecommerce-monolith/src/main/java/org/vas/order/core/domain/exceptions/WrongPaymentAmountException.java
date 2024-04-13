package org.vas.order.core.domain.exceptions;

public class WrongPaymentAmountException extends RuntimeException {
    public WrongPaymentAmountException() {
        super("The payment amount is wrong");
    }
}
