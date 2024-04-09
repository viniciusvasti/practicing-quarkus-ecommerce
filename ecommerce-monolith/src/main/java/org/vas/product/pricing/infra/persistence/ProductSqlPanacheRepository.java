package org.vas.product.pricing.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.vas.product.pricing.core.adapters.ProductRepository;
import org.vas.product.pricing.core.domain.Product;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductSqlPanacheRepository implements ProductRepository {

    @Override
    public Product saveProduct(Product product) {
        product.persist();
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Product.findByIdOptional(id);
    }

    @Override
    public Set<Product> findAllProducts() {
        List<Product> products = Product.listAll(Sort.by("sku").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<Product>(products);
    }

    @Override
    public void updateProduct(Product product) {
        Optional<Product> existingProduct = Product.findByIdOptional(product.id);
        existingProduct.ifPresent(pc -> {
            pc.setPrice(product.getPrice());
            pc.persist();
        });
    }

}
