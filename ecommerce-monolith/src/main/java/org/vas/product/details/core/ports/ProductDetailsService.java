package org.vas.product.details.core.ports;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.domain.ProductDetails;

public interface ProductDetailsService {
    Optional<ProductDetails> findById(Long id);
    Set<ProductDetails> listAll();
    ProductDetails create(ProductDetails product);
    void update(ProductDetails product);
}
