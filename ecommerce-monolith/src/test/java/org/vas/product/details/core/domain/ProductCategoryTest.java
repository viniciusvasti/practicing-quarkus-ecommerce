package org.vas.product.details.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductCategoryTest {
    @Test
    void testValidProductCategory() {
        ProductCategory productCategory = new ProductCategory("Electronics");
        assertEquals("Electronics", productCategory.getName());
        assertTrue(productCategory.isValid());
    }

    @Test
    void testInvalidProductCategoryShortName() {
        ProductCategory productCategory = new ProductCategory("E");
        assertEquals("E", productCategory.getName());
        assertFalse(productCategory.isValid());
    }

    @Test
    void testInvalidProductCategoryLongName() {
        ProductCategory productCategory = new ProductCategory(
                "ElectronicsXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        assertFalse(productCategory.isValid());
    }

    @Test
    void testInvalidProductCategoryNullName() {
        ProductCategory productCategory = new ProductCategory(null);
        assertFalse(productCategory.isValid());
    }
}
