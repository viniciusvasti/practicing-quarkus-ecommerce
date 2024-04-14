package org.vas.product.details.core.adapters;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.domain.ProductDetails;

public interface ProductDetailsRepository {
    ProductDetails saveProduct(ProductDetails product);
    Optional<ProductDetails> findProductById(Long id);
    Set<ProductDetails> findAllProducts();
    void updateProduct(ProductDetails product);
}
