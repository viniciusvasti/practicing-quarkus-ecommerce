package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductsCatalogControllerTest {
    @Test
    void testGetProductsCatalog() {
        given()
            .when().get("/products-catalog")
            .then()
            .statusCode(200)
            .body(is("products list"));
    }

    @Test
    void testGetProductById() {
        given()
            .when().get("/products-catalog/1")
            .then()
            .statusCode(200)
            .body(is("product by id: 1"));
    }

    @Test
    void testCreateProduct() {
        given()
            .when().post("/products-catalog")
            .then()
            .statusCode(200)
            .body(is("product created"));
    }

    @Test
    void testUpdateProduct() {
        given()
            .when().patch("/products-catalog/1")
            .then()
            .statusCode(200)
            .body(is("product updated: 1"));
    }

    @Test
    void testDeleteProduct() {
        given()
            .when().delete("/products-catalog/1")
            .then()
            .statusCode(200)
            .body(is("product deleted: 1"));
    }
}
