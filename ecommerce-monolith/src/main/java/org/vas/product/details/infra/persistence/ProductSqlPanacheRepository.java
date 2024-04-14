package org.vas.product.details.infra.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.vas.product.details.core.adapters.ProductRepository;
import org.vas.product.details.core.domain.Product;
import org.vas.product.details.core.domain.ProductCategory;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductSqlPanacheRepository implements ProductRepository {

    @Override
    public Product saveProduct(Product product) {
        // TODO: handle category not found
        Optional<ProductCategory> categoryOp = ProductCategory.findByIdOptional(product.getCategory().getId());
        if (categoryOp.isEmpty()) {
            throw new IllegalArgumentException("Category with id " + product.getCategory().getId() + " not found");
        }
        product.setCategory(categoryOp.get());
        product.persist();
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Product.findByIdOptional(id);
    }

    @Override
    public Set<Product> findAllProducts() {
        List<Product> products = Product.listAll(Sort.by("name").ascending());
        // LinkedHashSet to keep the order of list elements
        return new LinkedHashSet<Product>(products);
    }

    @Override
    public void updateProduct(Product product) {
        Optional<Product> existingProduct = Product.findByIdOptional(product.id);
        existingProduct.ifPresent(pc -> {
            Optional<ProductCategory> categoryOp = ProductCategory.findByIdOptional(product.getCategory().getId());
            if (categoryOp.isEmpty()) {
                throw new IllegalArgumentException("Category with id " + product.getCategory().getId() + " not found");
            }

            product.setCategory(categoryOp.get());
            pc.setName(product.getName());
            pc.setDescription(product.getDescription());
            pc.persistAndFlush();
        });
    }

}
