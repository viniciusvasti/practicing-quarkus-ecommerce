package org.vas.product.inventory.core.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void testValidProduct() {
        ProductInventory product = new ProductInventory("00000001", 300);
        assertTrue(product.isValid(), "Product should be valid");
    }

    @Test
    void testInvalidProductSku() {
        ProductInventory product = new ProductInventory("1", 300);
        assertFalse(product.isValid(), "Product SKU with short length should be invalid");

        product = new ProductInventory("000000001", 300);
        assertFalse(product.isValid(), "Product SKU with long length should be invalid");

        product = new ProductInventory(null, 300);
        assertFalse(product.isValid(), "Product SKU with null value should be invalid");
    }

    @Test
    void testInvalidProductStockUnits() {
        ProductInventory product = new ProductInventory("00000001", -1);
        assertFalse(product.isValid(), "Product stock units with negative value should be invalid");
    }
}
