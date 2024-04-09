package org.vas.product.pricing.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.vas.product.pricing.core.adapters.ProductRepository;
import org.vas.product.pricing.core.domain.Product;
import org.vas.product.pricing.presentation.dtos.CreateProductDTO;
import org.vas.product.pricing.presentation.dtos.UpdateProductDTO;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

@QuarkusTest
@TestHTTPEndpoint(ProductsController.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductsControllerIntegrationTest {
    @InjectSpy
    private ProductRepository productRepository;

    @Test
    @Order(1)
    void testGetProducts() {
        String responseBody = given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is(8))
                .extract().asString();

        assertEquals(
                "[{\"id\":1,\"sku\":\"00000001\",\"price\":29.99},{\"id\":51,\"sku\":\"00000002\",\"price\":37.99},{\"id\":101,\"sku\":\"00000003\",\"price\":1299.99},{\"id\":151,\"sku\":\"00000004\",\"price\":799.99},{\"id\":201,\"sku\":\"00000005\",\"price\":59.99},{\"id\":251,\"sku\":\"00000006\",\"price\":150.00},{\"id\":301,\"sku\":\"00000007\",\"price\":79.95},{\"id\":351,\"sku\":\"00000008\",\"price\":79.99}]",
                responseBody);

        assertTrue(responseBody.contains("{\"id\":1,\"sku\":\"00000001\",\"price\":29.99}"));
        assertTrue(responseBody.contains("{\"id\":51,\"sku\":\"00000002\",\"price\":37.99}"));
        assertTrue(responseBody.contains("{\"id\":101,\"sku\":\"00000003\",\"price\":1299.99}"));
        assertTrue(responseBody.contains("{\"id\":151,\"sku\":\"00000004\",\"price\":799.99}"));
        assertTrue(responseBody.contains("{\"id\":201,\"sku\":\"00000005\",\"price\":59.99}"));
        assertTrue(responseBody.contains("{\"id\":251,\"sku\":\"00000006\",\"price\":150.00}"));
        assertTrue(responseBody.contains("{\"id\":301,\"sku\":\"00000007\",\"price\":79.95}"));
        assertTrue(responseBody.contains("{\"id\":351,\"sku\":\"00000008\",\"price\":79.99}"));

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    @Order(2)
    void testGetProductById() {
        String responseBody = given()
                .when().get("/201")
                .then()
                .statusCode(200)
                .body("id", is(201))
                .body("sku", is("00000005"))
                .body("price", is(59.99f))
                .extract().asString();

        assertEquals("{\"id\":201,\"sku\":\"00000005\",\"price\":59.99}", responseBody);

        verify(productRepository, times(1)).findProductById(201l);
    }

    @Test
    void testGetProductByIdShouldReturnNotFound() {
        given()
                .when().get("/453453")
                .then()
                .statusCode(404)
                .body(is(""));

        verify(productRepository, times(1)).findProductById(453453l);
    }

    @Test
    void testGetProductByIdAsStringShouldReturnError() {
        given()
                .when().get("/aaaa")
                .then()
                .statusCode(404);

        verify(productRepository, times(0)).findProductById(any());
    }

    @Test
    void testCreateProduct() {
        CreateProductDTO createProductDTO = new CreateProductDTO("00000009", BigDecimal.valueOf(300.00));
        given()
                .header("Content-type", "application/json")
                .body(createProductDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("id", is(401))
                .body("sku", is("00000009"))
                .body("price", is(300.00f));

        var newProduct = productRepository.findProductById(401l).get();
        assertEquals("00000009", newProduct.getSku());
        assertEquals("300.00", newProduct.getPrice().toString());

        verify(productRepository, times(1)).saveProduct(any(Product.class));
    }

    @TestTransaction
    void testUpdateProduct() {
        Product newProduct = new Product("00000010", BigDecimal.valueOf(300.00));
        newProduct = productRepository.saveProduct(newProduct);
        Product.flush();

        UpdateProductDTO updateProductDTO = new UpdateProductDTO(newProduct.getId(), BigDecimal.valueOf(350.00));
        given()
                .header("Content-type", "application/json")
                .body(updateProductDTO)
                .when().patch("/" + newProduct.getId())
                .then()
                .statusCode(202)
                .body(is(""));

        var updatedProduct = productRepository.findProductById(newProduct.getId()).get();
        assertEquals("00000009", updatedProduct.getSku());
        assertEquals(BigDecimal.valueOf(350.00), updatedProduct.getPrice());

        verify(productRepository, times(1)).updateProduct(any(Product.class));
    }
}
