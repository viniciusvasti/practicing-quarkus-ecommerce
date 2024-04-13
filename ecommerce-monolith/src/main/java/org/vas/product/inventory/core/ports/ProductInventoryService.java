package org.vas.product.inventory.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.order.core.domain.Order;
import org.vas.product.inventory.core.domain.ProductInventory;

public interface ProductInventoryService {
    Optional<ProductInventory> findById(Long id);
    Set<ProductInventory> listAll();
    ProductInventory create(ProductInventory product);
    void update(ProductInventory product);
    List<ProductInventory> findBySkus(List<String> skus);
    void decreaseOrderStockUnits(Order order);
}
