package org.vas.store.application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vas.product.catalog.core.ports.ProductCategoryService;
import org.vas.product.catalog.core.ports.ProductService;
import org.vas.store.presentation.dtos.ProductCategoryDTO;
import org.vas.store.presentation.dtos.ProductDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationServiceImpl implements ApplicationService {
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private org.vas.product.pricing.core.ports.ProductService productPricingService;
    @Inject
    private ProductService productService;

    @Override
    public Set<ProductCategoryDTO> listProductsCatalog() {
        Map<Long, BigDecimal> prices = productPricingService.listAll().stream()
                .collect(HashMap::new, (map, p) -> map.put(p.getId(), p.getPrice()), HashMap::putAll);

        Map<Long, Set<ProductDTO>> categoryProductsMap = productService.listAll().stream()
                .map(p -> new ProductDTO(p.getId(), p.getSku(), p.getName(), prices.get(p.getId()), p.getDescription(), p.getCategory().getId()))
                .collect(HashMap::new, (map, p) -> {
                    Set<ProductDTO> products = map.getOrDefault(p.categoryId(), new HashSet<>());
                    products.add(p);
                    map.put(p.categoryId(), products);
                }, HashMap::putAll);

        List<ProductCategoryDTO> catalog = productCategoryService.listAll().stream()
                .map(cat -> new ProductCategoryDTO(cat.getId(), cat.getName(), categoryProductsMap.get(cat.getId())))
                .toList();
        return new LinkedHashSet<>(catalog);
    }
}
