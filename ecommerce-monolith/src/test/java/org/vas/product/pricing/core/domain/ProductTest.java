package org.vas.product.pricing.core.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void testValidProduct() {
        Product product = new Product("00000001", BigDecimal.valueOf(300.00));
        assertTrue(product.isValid(), "Product should be valid");
    }

    @Test
    void testInvalidProductSku() {
        Product product = new Product("1", BigDecimal.valueOf(300.00));
        assertFalse(product.isValid(), "Product SKU with short length should be invalid");

        product = new Product("000000001", BigDecimal.valueOf(300.00));
        assertFalse(product.isValid(), "Product SKU with long length should be invalid");

        product = new Product(null, BigDecimal.valueOf(300.00));
        assertFalse(product.isValid(), "Product SKU with null value should be invalid");
    }

    @Test
    void testInvalidProductPrice() {
        Product product = new Product("00000001", null);
        assertFalse(product.isValid(), "Product price with null value should be invalid");
    }
}
