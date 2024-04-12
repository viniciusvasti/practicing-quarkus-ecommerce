package org.vas.product.pricing.core.ports;

import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.domain.ProductPrice;

public interface ProductService {
    Optional<ProductPrice> findById(Long id);
    Set<ProductPrice> listAll();
    ProductPrice create(ProductPrice product);
    void update(ProductPrice product);
}
