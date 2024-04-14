package org.vas.product.catalog.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.vas.product.details.core.adapters.ProductRepository;
import org.vas.product.details.core.domain.Product;
import org.vas.product.details.core.domain.ProductCategory;
import org.vas.product.details.core.ports.ProductService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ProductServiceImplUnitTest {

    @Inject
    private ProductService productService;
    @InjectMock
    private ProductRepository productRepository;

    private final ProductCategory category = new ProductCategory("Electronics");
    private final Optional<Product> productBoseNC700 = Optional
            .of(new Product("00000001", "Bose NC 700", "Noise Cancelling Headphones", category));

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;
        when(productService.findById(id))
                .thenReturn(productBoseNC700);

        // When
        Optional<Product> result = productService.findById(id);

        // Then
        assertEquals(productBoseNC700, result);
        verify(productRepository, times(1))
                .findProductById(id);
    }

    @Test
    public void testFindAll() {
        // Given
        Product product1 = productBoseNC700.get();
        Product product2 = new Product("00000002", "Sony WH-1000XM4", "Noise Cancelling Headphones", category);
        when(productRepository.findAllProducts())
                .thenReturn(Set.of(product1, product2));
        when(productRepository.findAllProducts())
                .thenReturn(Set.of(product1, product2));

        // When
        Set<Product> result = productService.listAll();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
        verify(productRepository, times(1))
                .findAllProducts();
    }

    @Test
    public void testCreate() {
        // Given
        String sku = "00000001";
        String name = "Sony WH-1000XM4";
        String description = "Noise Cancelling Headphones";
        var product = new Product(1L, sku, name, description, new ProductCategory(category.getId(), ""));
        when(productRepository.saveProduct(any(Product.class)))
                .thenReturn(new Product(1L, "00000001", name, description, category));

        // When
        Product createdProduct = productService.create(product);

        // Then
        assertEquals(sku, createdProduct.getSku());
        assertEquals(name, createdProduct.getName());
        verify(productRepository, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        // Given
        Product product = productBoseNC700.get();
        when(productRepository.findProductById(product.getId()))
                .thenReturn(productBoseNC700);

        // When
        productService.update(product);

        // Then
        verify(productRepository, times(1))
                .updateProduct(product);
    }

}
