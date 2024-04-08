package org.vas.product.catalog.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {
    private final ProductCategory category = new ProductCategory("Electronics");

    @Test
    void testValidProduct() {
        Product product = new Product("Bose NC 700", "Noise Cancelling Headphones", category);
        assertEquals("Bose NC 700", product.getName());
        assertEquals("Noise Cancelling Headphones", product.getDescription());
        assertEquals(category, product.getCategory());
        assertTrue(product.isValid());
    }

    @Test
    void testInvalidProductShortName() {
        Product product = new Product("BN", "Noise Cancelling Headphones", category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductLongName() {
        Product product = new Product(
                "ElectronicsXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "Noise Cancelling Headphones", category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductNullName() {
        Product product = new Product(null, "Noise Cancelling Headphones", category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductShortDescription() {
        Product product = new Product("Bose NC 700", "NC", category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductLongDescription() {
        // 1001 characters
        String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Product product = new Product("Bose NC 700", longDescription, category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductNullDescription() {
        Product product = new Product("Bose NC 700", null, category);
        assertFalse(product.isValid());
    }

    @Test
    void testInvalidProductNullCategory() {
        Product product = new Product("Bose NC 700", "Noise Cancelling Headphones", null);
        assertFalse(product.isValid());
    }
}
