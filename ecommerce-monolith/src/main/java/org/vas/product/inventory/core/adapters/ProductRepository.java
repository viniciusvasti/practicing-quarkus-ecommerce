package org.vas.product.inventory.core.adapters;

import java.util.Optional;
import java.util.Set;

import org.vas.product.inventory.core.domain.Product;

public interface ProductRepository {
    Product saveProduct(Product product);
    Optional<Product> findProductById(Long id);
    Set<Product> findAllProducts();
    void updateProduct(Product product);
}
