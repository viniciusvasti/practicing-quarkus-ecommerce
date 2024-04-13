package org.vas.product.inventory.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.inventory.core.adapters.ProductRepository;
import org.vas.product.inventory.core.domain.ProductInventory;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductSqlPanacheRepository implements ProductRepository {

    @Override
    public ProductInventory saveProduct(ProductInventory product) {
        product.persist();
        return product;
    }

    @Override
    public Optional<ProductInventory> findProductById(Long id) {
        return ProductInventory.findByIdOptional(id);
    }

    @Override
    public Set<ProductInventory> findAllProducts() {
        List<ProductInventory> products = ProductInventory.listAll(Sort.by("sku").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<ProductInventory>(products);
    }

    @Override
    public void updateProduct(ProductInventory product) {
        Optional<ProductInventory> existingProduct = ProductInventory.findByIdOptional(product.id);
        existingProduct.ifPresent(pc -> {
            pc.setStockUnits(product.getStockUnits());
            pc.persist();
        });
    }

    @Override
    public List<ProductInventory> findBySkus(List<String> skus) {
        return ProductInventory.list("sku in ?1", skus);
    }

}
