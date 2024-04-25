package org.vas.product.inventory.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.order.core.domain.Order;
import org.vas.product.inventory.core.domain.ProductInventory;
import org.vas.product.inventory.presentation.dtos.CreateProductInventoryDTO;
import org.vas.product.inventory.presentation.dtos.UpdateProductInventoryDTO;

public interface ProductInventoryService {
    Optional<ProductInventory> findById(Long id);
    Set<ProductInventory> listAll();
    ProductInventory create(CreateProductInventoryDTO product);
    void update(UpdateProductInventoryDTO product, Long id);
    List<ProductInventory> findBySkus(List<String> skus);
    void decreaseOrderStockUnits(Order order);
}
