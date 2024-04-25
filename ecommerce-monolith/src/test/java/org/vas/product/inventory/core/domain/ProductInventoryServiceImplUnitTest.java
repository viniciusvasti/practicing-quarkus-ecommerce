package org.vas.product.inventory.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.vas.product.inventory.core.adapters.ProductInventoryRepository;
import org.vas.product.inventory.core.ports.ProductInventoryService;
import org.vas.product.inventory.presentation.dtos.CreateProductInventoryDTO;
import org.vas.product.inventory.presentation.dtos.UpdateProductInventoryDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ProductInventoryServiceImplUnitTest {

    @Inject
    private ProductInventoryService productService;
    @InjectMock
    private ProductInventoryRepository productRepository;

    private final Optional<ProductInventory> productBoseNC700 =
            Optional.of(new ProductInventory("00000001", 300));

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;
        when(productService.findById(id)).thenReturn(productBoseNC700);

        // When
        Optional<ProductInventory> result = productService.findById(id);

        // Then
        assertEquals(productBoseNC700, result);
        verify(productRepository, times(1)).findProductById(id);
    }

    @Test
    public void testFindAll() {
        // Given
        ProductInventory product1 = productBoseNC700.get();
        ProductInventory product2 = new ProductInventory("00000002", 280);
        when(productRepository.findAllProducts()).thenReturn(Set.of(product1, product2));
        when(productRepository.findAllProducts()).thenReturn(Set.of(product1, product2));

        // When
        Set<ProductInventory> result = productService.listAll();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    public void testCreate() {
        // Given
        String sku = "00000001";
        int stockUnits = 280;
        var product = new CreateProductInventoryDTO(sku, stockUnits);
        when(productRepository.saveProduct(any(ProductInventory.class)))
                .thenReturn(new ProductInventory(1L, "00000001", stockUnits));

        // When
        ProductInventory createdProduct = productService.create(product);

        // Then
        assertEquals(sku, createdProduct.getSku());
        assertEquals(stockUnits, createdProduct.getStockUnits());
        verify(productRepository, times(1)).saveProduct(any(ProductInventory.class));
    }

    @Test
    public void testUpdateProduct() {
        // Given
        UpdateProductInventoryDTO product = new UpdateProductInventoryDTO(1L, 280);
        when(productRepository.findProductById(product.id())).thenReturn(productBoseNC700);

        // When
        productService.update(product, product.id());

        // Then
        verify(productRepository, times(1)).updateProduct(any(ProductInventory.class));
    }

}
