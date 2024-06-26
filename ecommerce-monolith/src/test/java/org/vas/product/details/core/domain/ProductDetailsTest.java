package org.vas.product.details.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductDetailsTest {
    private final ProductCategory category = new ProductCategory("Electronics");

    @Test
    void testValidProduct() {
        ProductDetails product = new ProductDetails("00000001", "Bose NC 700", "Noise Cancelling Headphones", category);
        assertEquals("00000001", product.getSku());
        assertEquals("Bose NC 700", product.getName());
        assertEquals("Noise Cancelling Headphones", product.getDescription());
        assertEquals(category, product.getCategory());
        assertTrue(product.isValid(), "Product should be valid");
    }

    @Test
    void testInvalidProductSku() {
        ProductDetails product = new ProductDetails("1", "Bose NC 700", "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product SKU with short length should be invalid");

        product = new ProductDetails("000000001", "Bose NC 700", "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product SKU with long length should be invalid");

        product = new ProductDetails(null, "Bose NC 700", "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product SKU with null value should be invalid");
    }

    @Test
    void testInvalidProductName() {
        ProductDetails product = new ProductDetails("00000001", "BN", "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product name with short length should be invalid");

        product = new ProductDetails("00000001",
                "ElectronicsXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product name with long length should be invalid");

        product = new ProductDetails("00000001", null, "Noise Cancelling Headphones", category);
        assertFalse(product.isValid(), "Product name with null value should be invalid");
    }

    @Test
    void testInvalidProductDescription() {
        ProductDetails product = new ProductDetails("00000001", "Bose NC 700", "NC", category);
        assertFalse(product.isValid(), "Product description with short length should be invalid");

        String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        product = new ProductDetails("00000001", "Bose NC 700", longDescription, category);
        assertFalse(product.isValid(), "Product description with long length should be invalid");

        product = new ProductDetails("00000001", "Bose NC 700", null, category);
        assertFalse(product.isValid(), "Product description with null value should be invalid");
    }

    @Test
    void testInvalidProductNullCategory() {
        ProductDetails product = new ProductDetails("00000001", "Bose NC 700", "Noise Cancelling Headphones", null);
        assertFalse(product.isValid(), "Product category with null value should be invalid");
    }
}
