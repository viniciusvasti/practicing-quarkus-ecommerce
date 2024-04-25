package org.vas.product.details.core.ports;

import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.domain.ProductDetails;
import org.vas.product.details.presentation.dtos.CreateProductDetailsDTO;
import org.vas.product.details.presentation.dtos.UpdateProductDetailsDTO;

public interface ProductDetailsService {
    Optional<ProductDetails> findById(Long id);
    Set<ProductDetails> listAll();
    ProductDetails create(CreateProductDetailsDTO product);
    void update(UpdateProductDetailsDTO product, Long id);
}
