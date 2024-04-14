package org.vas.store.application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.vas.product.details.core.ports.ProductCategoryService;
import org.vas.product.details.core.ports.ProductDetailsService;
import org.vas.store.presentation.dtos.ProductCategoryDTO;
import org.vas.store.presentation.dtos.ProductDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationServiceImpl implements ApplicationService {
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private org.vas.product.pricing.core.ports.ProductPriceService productPricingService;
    @Inject
    private org.vas.product.inventory.core.ports.ProductInventoryService productInventoryService;
    @Inject
    private ProductDetailsService productService;

    @Override
    public Set<ProductCategoryDTO> listProductsCatalog() {
        Map<String, BigDecimal> prices = productPricingService.listAll().stream()
                .collect(HashMap::new, (map, p) -> map.put(p.getSku(), p.getPrice()), HashMap::putAll);

        Map<String, Integer> stockUnits = productInventoryService.listAll().stream()
                .filter(p -> p.getStockUnits() > 0)
                .collect(HashMap::new, (map, p) -> map.put(p.getSku(), p.getStockUnits()), HashMap::putAll);

        Map<Long, Set<ProductDTO>> categoryProductsMap = productService.listAll().stream()
                .filter(p -> stockUnits.containsKey(p.getSku()) && stockUnits.get(p.getSku()) > 0
                        && prices.containsKey(p.getSku()))
                .map(p -> new ProductDTO(p.getId(), p.getSku(), p.getName(), prices.get(p.getSku()), p.getDescription(),
                        p.getCategory().getId()))
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
