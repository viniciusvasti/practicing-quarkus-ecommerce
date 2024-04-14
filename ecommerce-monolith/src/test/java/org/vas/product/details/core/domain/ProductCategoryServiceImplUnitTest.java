package org.vas.product.details.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.ports.ProductCategoryService;
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
        Long id = 1L;
        var productCategory = Optional.of(new ProductCategory("Electronics"));
        when(productCategoryService.findById(id))
                .thenReturn(productCategory);

        // When
        Optional<ProductCategory> result = productCategoryService.findById(id);

        // Then
        assertEquals(productCategory, result);
        verify(productCategoryRepository, times(1))
                .findProductCategoryById(id);
    }

    @Test
    public void testFindAll() {
        // Given
        ProductCategory productCategory1 = new ProductCategory("Electronics");
        ProductCategory productCategory2 = new ProductCategory("Books");
        when(productCategoryRepository.findAllProductCategories())
                .thenReturn(Set.of(productCategory1, productCategory2));

        // When
        Set<ProductCategory> result = productCategoryService.listAll();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(productCategory1));
        assertTrue(result.contains(productCategory2));
        verify(productCategoryRepository, times(1))
                .findAllProductCategories();
    }

    @Test
    public void testCreate() {
        // Given
        String name = "Electronics";

        // When
        ProductCategory productCategory = productCategoryService.create(name);

        // Then
        assertEquals(name, productCategory.getName());
        verify(productCategoryRepository, times(1))
                .saveProductCategory(new ProductCategory(name));
    }

    @Test
    public void testUpdateProductCategory() {
        // Given
        ProductCategory productCategory = new ProductCategory("Electronics");

        // When
        productCategoryService.update(productCategory);

        // Then
        verify(productCategoryRepository, times(1))
                .updateProductCategory(productCategory);
    }

}
