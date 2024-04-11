package org.vas.product.pricing.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.hamcrest.CoreMatchers;
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
        var productsCount = Product.count();

        given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is((int) productsCount))
                .body("[0].id", CoreMatchers.any(Integer.class))
                .body("[0].sku", is("00000001"))
                .body("[0].price", is(29.99f))
                .body("[1].id", CoreMatchers.any(Integer.class))
                .body("[1].sku", is("00000002"))
                .body("[1].price", is(37.99f))
                .body("[2].id", CoreMatchers.any(Integer.class))
                .body("[2].sku", is("00000003"))
                .body("[2].price", is(1299.99f))
                .body("[3].id", CoreMatchers.any(Integer.class))
                .body("[3].sku", is("00000004"))
                .body("[3].price", is(799.99f))
                .body("[4].id", CoreMatchers.any(Integer.class))
                .body("[4].sku", is("00000005"))
                .body("[4].price", is(59.99f))
                .body("[5].id", CoreMatchers.any(Integer.class))
                .body("[5].sku", is("00000006"))
                .body("[5].price", is(150.00f))
                .body("[6].id", CoreMatchers.any(Integer.class))
                .body("[6].sku", is("00000007"))
                .body("[6].price", is(79.95f))
                .body("[7].id", CoreMatchers.any(Integer.class))
                .body("[7].sku", is("00000008"))
                .body("[7].price", is(79.99f));

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
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

        Product product = given()
                .header("Content-type", "application/json")
                .body(createProductDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("id", is(CoreMatchers.any(Integer.class)))
                .body("sku", is("00000009"))
                .body("price", is(300.00f))
                .extract().as(Product.class);

        var newProduct = productRepository.findProductById(product.id).get();
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
