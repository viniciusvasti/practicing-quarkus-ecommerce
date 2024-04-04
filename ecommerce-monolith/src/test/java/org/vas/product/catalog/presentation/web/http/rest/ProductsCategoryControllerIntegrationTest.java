package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(ProductsCategoryController.class)
public class ProductsCategoryControllerIntegrationTest {
    @Test
    void testGetProductsCatalog() {
        String responseBody = given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("[0].name", is("category 1"))
                .body("[1].name", is("category 2"))
                .extract().asString();

        // TODO: verify why the ids are null
        assertEquals("[{\"id\":null,\"name\":\"category 1\"},{\"id\":null,\"name\":\"category 2\"}]", responseBody);
    }

    @Test
    void testGetProductById() {
        given()
                .when().get("/1")
                .then()
                .statusCode(200)
                .body(is("product by id: 1"));
    }

    @Test
    void testCreateProduct() {
        given()
                .when().post("")
                .then()
                .statusCode(200)
                .body(is("product created"));
    }

    @Test
    void testUpdateProduct() {
        given()
                .when().patch("/1")
                .then()
                .statusCode(200)
                .body(is("product updated: 1"));
    }

    @Test
    void testDeleteProduct() {
        given()
                .when().delete("/1")
                .then()
                .statusCode(200)
                .body(is("product deleted: 1"));
    }
}
