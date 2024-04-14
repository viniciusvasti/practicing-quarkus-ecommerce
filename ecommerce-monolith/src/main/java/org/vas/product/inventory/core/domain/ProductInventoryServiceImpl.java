package org.vas.product.inventory.core.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.vas.order.core.domain.Order;
import org.vas.order.core.domain.OrderedProduct;
import org.vas.order.core.domain.exceptions.NotEnoughStockUnitsException;
import org.vas.product.inventory.core.adapters.ProductInventoryRepository;
import org.vas.product.inventory.core.ports.ProductInventoryService;
import org.vas.product.inventory.presentation.dtos.CreateProductInventoryDTO;
import org.vas.product.inventory.presentation.dtos.UpdateProductInventoryDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Inject
    private ProductInventoryRepository productRepository;

    public Optional<ProductInventory> findById(Long id) {
        return productRepository.findProductById(id);
    }

    public Set<ProductInventory> listAll() {
        return productRepository.findAllProducts();
    }

    public ProductInventory create(CreateProductInventoryDTO productDto) {
        var product = new ProductInventory(productDto.sku(), productDto.stockUnits());
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        return productRepository.saveProduct(product);
    }

    public void update(UpdateProductInventoryDTO productDto, Long id) {
        var product = new ProductInventory(id, null, productDto.stockUnits());
        var existingProduct = productRepository.findProductById(product.id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + product.id + " not found"));
        product.setSku(existingProduct.getSku());
        if (!product.isValid()) {
            throw new IllegalArgumentException("Invalid product ");
        }
        productRepository.updateProduct(product);
    }

    @Override
    public List<ProductInventory> findBySkus(List<String> skus) {
        return productRepository.findBySkus(skus);
    }

    @Override
    public void decreaseOrderStockUnits(Order order) {
        Map<String, Integer> skusQuantity = order.getItems().stream()
                .collect(Collectors.toMap(OrderedProduct::getSku, OrderedProduct::getQuantity));
        List<ProductInventory> inventories = findBySkus(skusQuantity.keySet().stream().toList());
        for (ProductInventory inventory : inventories) {
            int orderedQuantity = skusQuantity.get(inventory.getSku());
            if (inventory.getStockUnits() < orderedQuantity) {
                throw new NotEnoughStockUnitsException(inventory.getSku());
            }
            inventory.decreaseStockUnits(orderedQuantity);
            productRepository.updateProduct(inventory);
        }
    }
}
