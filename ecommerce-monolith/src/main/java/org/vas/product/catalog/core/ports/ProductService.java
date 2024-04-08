package org.vas.product.catalog.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.product.catalog.core.domain.Product;
import org.vas.product.catalog.core.domain.ProductCategory;

public interface ProductService {
    Optional<Product> findById(Long id);
    Set<Product> listAll();
    Product create(String name, String description, Long categoryId);
    void update(Product product);
}
