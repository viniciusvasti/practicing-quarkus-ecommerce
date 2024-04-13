package org.vas.product.pricing.core.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.domain.ProductPrice;

public interface ProductRepository {
    ProductPrice saveProduct(ProductPrice product);
    Optional<ProductPrice> findProductById(Long id);
    Set<ProductPrice> findAllProducts();
    void updateProduct(ProductPrice product);
    List<ProductPrice> findBySkus(List<String> skus);
}
