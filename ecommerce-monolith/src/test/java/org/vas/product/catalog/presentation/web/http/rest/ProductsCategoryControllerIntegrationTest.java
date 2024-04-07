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
                .body("size()", is(4))
                .extract().asString();

        assertEquals("[{\"id\":1,\"name\":\"Books\"},{\"id\":101,\"name\":\"Clothing\"},{\"id\":51,\"name\":\"Electronics\"},{\"id\":151,\"name\":\"Home & Kitchen\"}]", responseBody);

        assertTrue(responseBody.contains("{\"id\":1,\"name\":\"Books\"}"));
        assertTrue(responseBody.contains("{\"id\":51,\"name\":\"Electronics\"}"));
        assertTrue(responseBody.contains("{\"id\":101,\"name\":\"Clothing\"}"));
        assertTrue(responseBody.contains("{\"id\":151,\"name\":\"Home & Kitchen\"}"));
    }

    @Test
    void testGetProductById() {
        String responseBody = given()
                .when().get("/101")
                .then()
                .statusCode(200)
                .body("name", is("Clothing"))
                .body("id", is(101))
                .extract().asString();

        assertEquals("{\"id\":101,\"name\":\"Clothing\"}", responseBody);
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
                .body("id", is(201));
    }

    @Test
    void testUpdateProduct() {
        UpdateProductCategoryDTO updateProductCategoryDTO = new UpdateProductCategoryDTO(201l, "Updated Category");
        given()
                .header("Content-type", "application/json")
                .body(updateProductCategoryDTO)
                .when().patch("/201")
                .then()
                .statusCode(202)
                .body(is(""));

        var updatedProduct = productCategoryRepository.findProductCategoryById(201l).get();
        assertEquals("Updated Category", updatedProduct.getName());
    }
}
