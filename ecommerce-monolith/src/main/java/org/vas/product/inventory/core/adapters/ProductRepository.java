package org.vas.product.inventory.core.adapters;

import java.util.Optional;
import java.util.Set;

import org.vas.product.inventory.core.domain.ProductInventory;

public interface ProductRepository {
    ProductInventory saveProduct(ProductInventory product);
    Optional<ProductInventory> findProductById(Long id);
    Set<ProductInventory> findAllProducts();
    void updateProduct(ProductInventory product);
}
