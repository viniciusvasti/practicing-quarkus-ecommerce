package org.vas.product.pricing.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.domain.Product;

public interface ProductService {
    Optional<Product> findById(Long id);
    Set<Product> listAll();
    Product create(Product product);
    void update(Product product);
}
