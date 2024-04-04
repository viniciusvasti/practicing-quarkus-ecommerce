package org.vas.product.catalog.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.vas.product.catalog.core.adapters.ProductCategoryRepository;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ProductCategoryServiceImplUnitTest {

    @Inject
    private ProductCategoryService productCategoryService;
    @InjectMock
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindById() {
        // Given
        String id = "1";
        ProductCategory productCategory = new ProductCategory("Electronics");
        Mockito.when(productCategoryService.findById(id)).thenReturn(productCategory);

        // When
        ProductCategory result = productCategoryService.findById(id);

        // Then
        assertEquals(productCategory, result);
    }

    @Test
    public void testFindAll() {
        // Given
        ProductCategory productCategory1 = new ProductCategory("Electronics");
        ProductCategory productCategory2 = new ProductCategory("Books");
        Mockito.when(productCategoryService.listAll()).thenReturn(Set.of(productCategory1, productCategory2));

        // When
        Set<ProductCategory> result = productCategoryService.listAll();

        // Then
        assertEquals(2, result.size());
        var iterator = result.iterator();
        assertEquals(productCategory1, iterator.next());
        assertEquals(productCategory2, iterator.next());
    }

    @Test
    public void testCreate() {
        // Given
        String name = "Electronics";

        // When
        ProductCategory productCategory = productCategoryService.create(name);

        // Then
        assertEquals(name, productCategory.getName());
    }

    @Test
    public void testUpdateProductCategory() {
        // Given
        ProductCategory productCategory = new ProductCategory("Electronics");

        // When
        productCategoryService.update(productCategory);

        // Then
        Mockito.verify(productCategoryRepository, Mockito.times(1)).updateProductCategory(productCategory);
    }

}
