package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.vas.product.catalog.core.adapters.ProductCategoryRepository;
import org.vas.product.catalog.presentation.dtos.CreateProductCategoryDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductCategoryDTO;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(ProductsCategoryController.class)
public class ProductsCategoryControllerIntegrationTest {
    @Inject
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void testGetProductsCatalog() {
        String responseBody = given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is(3))
                .extract().asString();

        // TODO: verify why it's not ordered
        // assertEquals(
        // "[{\"id\": 1,\"name\":\"category 1\"},{\"id\": 2,\"name\":\"category
        // 2\"},{\"id\":3,\"name\":\"category 3\"}]",
        // responseBody);

        assertTrue(responseBody.contains("{\"id\":1,\"name\":\"category 1\"}"));
        assertTrue(responseBody.contains("{\"id\":2,\"name\":\"category 2\"}"));
        assertTrue(responseBody.contains("{\"id\":3,\"name\":\"category 3\"}"));
    }

    @Test
    void testGetProductById() {
        String responseBody = given()
                .when().get("/2")
                .then()
                .statusCode(200)
                .body("name", is("category 2"))
                .body("id", is(2))
                .extract().asString();

        assertEquals("{\"id\":2,\"name\":\"category 2\"}", responseBody);
    }

    @Test
    void testGetProductByIdShouldReturnNotFound() {
        given()
                .when().get("/100")
                .then()
                .statusCode(404)
                .body(is(""));
    }

    @Test
    void testCreateProduct() {
        CreateProductCategoryDTO createProductCategoryDTO = new CreateProductCategoryDTO("New Category");
        given()
                .header("Content-type", "application/json")
                .body(createProductCategoryDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("name", is("New Category"))
                .body("id", is(4));
    }

    @Test
    void testUpdateProduct() {
        UpdateProductCategoryDTO updateProductCategoryDTO = new UpdateProductCategoryDTO(4l, "Updated Category");
        given()
                .header("Content-type", "application/json")
                .body(updateProductCategoryDTO)
                .when().patch("/4")
                .then()
                .statusCode(202)
                .body(is(""));

        var updatedProduct = productCategoryRepository.findProductCategoryById(4l).get();
        assertEquals("Updated Category", updatedProduct.getName());
    }
}
