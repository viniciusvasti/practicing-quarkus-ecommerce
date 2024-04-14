package org.vas.product.pricing.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.domain.ProductPrice;
import org.vas.product.pricing.presentation.dtos.CreateProductPriceDTO;
import org.vas.product.pricing.presentation.dtos.UpdateProductPriceDTO;

public interface ProductPriceService {
    Optional<ProductPrice> findById(Long id);
    Set<ProductPrice> listAll();
    ProductPrice create(CreateProductPriceDTO product);
    void update(UpdateProductPriceDTO product, Long id);
    List<ProductPrice> findBySkus(List<String> skus);
}
