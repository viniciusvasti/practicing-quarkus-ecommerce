package org.vas.product.details.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.adapters.ProductDetailsRepository;
import org.vas.product.details.core.domain.ProductCategory;
import org.vas.product.details.core.domain.ProductDetails;
import org.vas.product.details.presentation.dtos.CreateProductDetailsDTO;
import org.vas.product.details.presentation.dtos.UpdateProductDetailsDTO;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
@TestHTTPEndpoint(ProductDetailsResource.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductDetailsResourceIntegrationTest {
    @InjectSpy
    private ProductDetailsRepository productRepository;
    @Inject
    private ProductCategoryRepository productCategoryRepository;
    private static ProductDetails productToBeUpdated;

    @BeforeAll
    @Transactional
    public static void setup() {
        ProductCategory category = new ProductCategory("Test");
        category.persist();
        productToBeUpdated = new ProductDetails("42342342", "To be updated",
                "Product to be update during patch test", category);
        productToBeUpdated.persist();
    }

    @Test
    @Order(1)
    void testGetProducts() {
        List<ProductDetails> products = ProductDetails.listAll();
        products.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        List<ProductDetails> fetchedProducts = given().when().get("").then().statusCode(200)
                .body("size()", is((int) products.size())).extract().jsonPath()
                .getList(".", ProductDetails.class);
        fetchedProducts.forEach(p -> {
            int index = fetchedProducts.indexOf(p);
            assertEquals(products.get(index).getName(), p.getName());
            assertEquals(products.get(index).getId(), p.getId());
            assertEquals(products.get(index).getSku(), p.getSku());
            assertEquals(products.get(index).getDescription(), p.getDescription());
            assertEquals(products.get(index).getCategory().getId(),
                    p.getCategory().getId());
            assertEquals(products.get(index).getCategory().getName(),
                    p.getCategory().getName());
        });

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    void testGetProductById() {
        String responseBody = given().when().get("/351").then().statusCode(200).body("id", is(351))
                .body("name", is("Keurig K-Classic Coffee Maker"))
                .body("description", is("Brews multiple K-Cup Pod sizes"))
                .body("category.id", is(151)).body("category.name", is("Home & Kitchen")).extract()
                .asString();

        assertEquals(
                "{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"description\":\"Brews multiple K-Cup Pod sizes\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}}",
                responseBody);

        verify(productRepository, times(1)).findProductById(351l);
    }

    @Test
    void testGetProductByIdShouldReturnNotFound() {
        given().when().get("/453453").then().statusCode(404).body(is(""));

        verify(productRepository, times(1)).findProductById(453453l);
    }

    @Test
    void testGetProductByIdAsStringShouldReturnError() {
        given().when().get("/aaaa").then().statusCode(404);

        verify(productRepository, times(0)).findProductById(any());
    }

    @Test
    @TestTransaction
    void testCreateProduct() {
        ProductCategory category =
                productCategoryRepository.findAllProductCategories().iterator().next();
        CreateProductDetailsDTO createProductDTO = new CreateProductDetailsDTO("00000009",
                "New Product", "New Description", category.id);
        ProductDetails product = given().header("Content-type", "application/json")
                .body(createProductDTO).when().post("").then().statusCode(201)
                .body("id", is(CoreMatchers.any(Integer.class))).body("sku", is("00000009"))
                .body("name", is("New Product")).body("description", is("New Description"))
                .body("category.id", is(Long.valueOf(category.id).intValue()))
                .body("category.name", is(category.getName())).extract().as(ProductDetails.class);

        var newProduct = productRepository.findProductById(product.id).get();
        assertEquals("00000009", newProduct.getSku());
        assertEquals("New Product", newProduct.getName());
        assertEquals("New Description", newProduct.getDescription());
        assertEquals(category.id, newProduct.getCategory().getId());
        assertEquals(category.getName(), newProduct.getCategory().getName());

        verify(productRepository, times(1)).saveProduct(any(ProductDetails.class));
    }

    @Test
    void testUpdateProduct() {
        UpdateProductDetailsDTO updateProductDTO =
                new UpdateProductDetailsDTO(productToBeUpdated.getId(), "Updated Product",
                        "Updated Description", productToBeUpdated.getCategory().id);
        given().header("Content-type", "application/json").body(updateProductDTO).when()
                .patch("/" + productToBeUpdated.getId()).then().statusCode(202).body(is(""));

        verify(productRepository, times(1)).updateProduct(any(ProductDetails.class));
    }
}
