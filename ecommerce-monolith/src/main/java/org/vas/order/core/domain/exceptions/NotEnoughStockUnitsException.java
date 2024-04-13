package org.vas.order.core.domain.exceptions;

public class NotEnoughStockUnitsException extends RuntimeException {
    public NotEnoughStockUnitsException(String sku) {
        super("Not enough items on inventory for product with sku " + sku);
    }
}
