package org.vas.product.pricing.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.vas.product.pricing.core.adapters.ProductRepository;
import org.vas.product.pricing.core.ports.ProductPriceService;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ProductServiceImplUnitTest {

    @Inject
    private ProductPriceService productService;
    @InjectMock
    private ProductRepository productRepository;

    private final Optional<ProductPrice> productBoseNC700 = Optional.of(new ProductPrice("00000001", BigDecimal.valueOf(300.00)));

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;
        when(productService.findById(id))
                .thenReturn(productBoseNC700);

        // When
        Optional<ProductPrice> result = productService.findById(id);

        // Then
        assertEquals(productBoseNC700, result);
        verify(productRepository, times(1))
                .findProductById(id);
    }

    @Test
    public void testFindAll() {
        // Given
        ProductPrice product1 = productBoseNC700.get();
        ProductPrice product2 = new ProductPrice("00000002", BigDecimal.valueOf(280.00));
        when(productRepository.findAllProducts())
                .thenReturn(Set.of(product1, product2));
        when(productRepository.findAllProducts())
                .thenReturn(Set.of(product1, product2));

        // When
        Set<ProductPrice> result = productService.listAll();

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
        BigDecimal price = BigDecimal.valueOf(280.00);
        var product = new ProductPrice(1L, sku, price);
        when(productRepository.saveProduct(any(ProductPrice.class)))
                .thenReturn(new ProductPrice(1L, "00000001", price));

        // When
        ProductPrice createdProduct = productService.create(product);

        // Then
        assertEquals(sku, createdProduct.getSku());
        assertEquals(price, createdProduct.getPrice());
        verify(productRepository, times(1)).saveProduct(any(ProductPrice.class));
    }

    @Test
    public void testUpdateProduct() {
        // Given
        ProductPrice product = productBoseNC700.get();
        when(productRepository.findProductById(product.getId()))
                .thenReturn(productBoseNC700);

        // When
        productService.update(product);

        // Then
        verify(productRepository, times(1))
                .updateProduct(product);
    }

}
