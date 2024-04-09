package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.vas.product.catalog.core.adapters.ProductRepository;
import org.vas.product.catalog.core.domain.Product;
import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.presentation.dtos.CreateProductDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductDTO;

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
                "[{\"id\":51,\"sku\":\"00000002\",\"name\":\"Clean Code\",\"description\":\"A Handbook of Agile Software Craftsmanship\",\"category\":{\"id\":1,\"name\":\"Books\"}},{\"id\":301,\"sku\":\"00000007\",\"name\":\"Instant Pot Duo 7-in-1 Electric Pressure Cooker\",\"description\":\"The best-selling model\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}},{\"id\":151,\"sku\":\"00000004\",\"name\":\"iPhone 12\",\"description\":\"Blast past fast\",\"category\":{\"id\":51,\"name\":\"Electronics\"}},{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"description\":\"Brews multiple K-Cup Pod sizes\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}},{\"id\":201,\"sku\":\"00000005\",\"name\":\"Levi's 501 Original Fit Jeans\",\"description\":\"The original blue jean since 1873\",\"category\":{\"id\":101,\"name\":\"Clothing\"}},{\"id\":101,\"sku\":\"00000003\",\"name\":\"MacBook Pro\",\"description\":\"The ultimate pro notebook\",\"category\":{\"id\":51,\"name\":\"Electronics\"}},{\"id\":251,\"sku\":\"00000006\",\"name\":\"Nike Air Max 270\",\"description\":\"The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93\",\"category\":{\"id\":101,\"name\":\"Clothing\"}},{\"id\":1,\"sku\":\"00000001\",\"name\":\"The Pragmatic Programmer\",\"description\":\"Your journey to mastery\",\"category\":{\"id\":1,\"name\":\"Books\"}}]",
                responseBody);

        assertTrue(responseBody.contains(
                "{\"id\":51,\"sku\":\"00000002\",\"name\":\"Clean Code\",\"description\":\"A Handbook of Agile Software Craftsmanship\",\"category\":{\"id\":1,\"name\":\"Books\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":301,\"sku\":\"00000007\",\"name\":\"Instant Pot Duo 7-in-1 Electric Pressure Cooker\",\"description\":\"The best-selling model\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":151,\"sku\":\"00000004\",\"name\":\"iPhone 12\",\"description\":\"Blast past fast\",\"category\":{\"id\":51,\"name\":\"Electronics\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"description\":\"Brews multiple K-Cup Pod sizes\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":201,\"sku\":\"00000005\",\"name\":\"Levi's 501 Original Fit Jeans\",\"description\":\"The original blue jean since 1873\",\"category\":{\"id\":101,\"name\":\"Clothing\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":101,\"sku\":\"00000003\",\"name\":\"MacBook Pro\",\"description\":\"The ultimate pro notebook\",\"category\":{\"id\":51,\"name\":\"Electronics\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":251,\"sku\":\"00000006\",\"name\":\"Nike Air Max 270\",\"description\":\"The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93\",\"category\":{\"id\":101,\"name\":\"Clothing\"}}"));
        assertTrue(responseBody.contains(
                "{\"id\":1,\"sku\":\"00000001\",\"name\":\"The Pragmatic Programmer\",\"description\":\"Your journey to mastery\",\"category\":{\"id\":1,\"name\":\"Books\"}}"));

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    @Order(2)
    void testGetProductById() {
        String responseBody = given()
                .when().get("/351")
                .then()
                .statusCode(200)
                .body("id", is(351))
                .body("name", is("Keurig K-Classic Coffee Maker"))
                .body("description", is("Brews multiple K-Cup Pod sizes"))
                .body("category.id", is(151))
                .body("category.name", is("Home & Kitchen"))
                .extract().asString();

        assertEquals(
                "{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"description\":\"Brews multiple K-Cup Pod sizes\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}}",
                responseBody);

        verify(productRepository, times(1)).findProductById(351l);
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
        CreateProductDTO createProductDTO = new CreateProductDTO("00000009", "New Product", "New Description", 101l);
        given()
                .header("Content-type", "application/json")
                .body(createProductDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("id", is(401))
                .body("sku", is("00000009"))
                .body("name", is("New Product"))
                .body("description", is("New Description"))
                .body("category.id", is(101))
                .body("category.name", is("Clothing"));

        var newProduct = productRepository.findProductById(401l).get();
        assertEquals("00000009", newProduct.getSku());
        assertEquals("New Product", newProduct.getName());
        assertEquals("New Description", newProduct.getDescription());
        assertEquals(101l, newProduct.getCategory().getId());
        assertEquals("Clothing", newProduct.getCategory().getName());

        verify(productRepository, times(1)).saveProduct(any(Product.class));
    }

    @TestTransaction
    void testUpdateProduct() {
        Product newProduct = new Product("00000010", "Product to be updated", "Description",
                new ProductCategory(101l, null));
        newProduct = productRepository.saveProduct(newProduct);
        Product.flush();

        UpdateProductDTO updateProductDTO = new UpdateProductDTO(newProduct.getId(), "Updated Product",
                "Updated Description", 101l);
        given()
                .header("Content-type", "application/json")
                .body(updateProductDTO)
                .when().patch("/" + newProduct.getId())
                .then()
                .statusCode(202)
                .body(is(""));

        var updatedProduct = productRepository.findProductById(newProduct.getId()).get();
        assertEquals("00000009", updatedProduct.getSku());
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals("Updated Description", updatedProduct.getDescription());

        verify(productRepository, times(1)).updateProduct(any(Product.class));
    }
}
