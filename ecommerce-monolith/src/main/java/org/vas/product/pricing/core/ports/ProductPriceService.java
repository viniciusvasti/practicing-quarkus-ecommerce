package org.vas.product.pricing.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.domain.ProductPrice;

public interface ProductPriceService {
    Optional<ProductPrice> findById(Long id);
    Set<ProductPrice> listAll();
    ProductPrice create(ProductPrice product);
    void update(ProductPrice product);
    List<ProductPrice> findBySkus(List<String> skus);
}
