package org.vas.product.inventory.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.product.inventory.core.domain.ProductInventory;

public interface ProductService {
    Optional<ProductInventory> findById(Long id);
    Set<ProductInventory> listAll();
    ProductInventory create(ProductInventory product);
    void update(ProductInventory product);
}
