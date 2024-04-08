package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.vas.product.catalog.core.adapters.ProductCategoryRepository;
import org.vas.product.catalog.presentation.dtos.CreateProductCategoryDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductCategoryDTO;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

@QuarkusTest
@TestHTTPEndpoint(ProductsCategoryController.class)
public class ProductsCategoryControllerIntegrationTest {
    @InjectSpy
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void testGetProductCategories() {
        String responseBody = given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is(4))
                .extract().asString();

        assertEquals(
                "[{\"id\":1,\"name\":\"Books\"},{\"id\":101,\"name\":\"Clothing\"},{\"id\":51,\"name\":\"Electronics\"},{\"id\":151,\"name\":\"Home & Kitchen\"}]",
                responseBody);

        assertTrue(responseBody.contains("{\"id\":1,\"name\":\"Books\"}"));
        assertTrue(responseBody.contains("{\"id\":51,\"name\":\"Electronics\"}"));
        assertTrue(responseBody.contains("{\"id\":101,\"name\":\"Clothing\"}"));
        assertTrue(responseBody.contains("{\"id\":151,\"name\":\"Home & Kitchen\"}"));

        verify(productCategoryRepository, times(1)).findAllProductCategories();
    }

    @Test
    void testGetProductCategoryById() {
        String responseBody = given()
                .when().get("/101")
                .then()
                .statusCode(200)
                .body("name", is("Clothing"))
                .body("id", is(101))
                .extract().asString();

        assertEquals("{\"id\":101,\"name\":\"Clothing\"}", responseBody);

        verify(productCategoryRepository, times(1)).findProductCategoryById(101l);
    }

    @Test
    void testGetProductCategoryByIdShouldReturnNotFound() {
        given()
                .when().get("/100")
                .then()
                .statusCode(404)
                .body(is(""));

        verify(productCategoryRepository, times(1)).findProductCategoryById(100l);
    }

    @Test
    void testGetProductCategoryByIdAsStringShouldReturnError() {
        given()
                .when().get("/aaaa")
                .then()
                .statusCode(404);

        verify(productCategoryRepository, times(0)).findProductCategoryById(any());
    }

    @Test
    void testCreateProductCategory() {
        CreateProductCategoryDTO createProductCategoryDTO = new CreateProductCategoryDTO("New Category");
        given()
                .header("Content-type", "application/json")
                .body(createProductCategoryDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("name", is("New Category"))
                .body("id", is(201));

        var newProduct = productCategoryRepository.findProductCategoryById(201l).get();
        assertEquals("New Category", newProduct.getName());

        verify(productCategoryRepository, times(1)).saveProductCategory(newProduct);
    }

    @Test
    void testUpdateProductCategory() {
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

        verify(productCategoryRepository, times(1)).updateProductCategory(updatedProduct);
    }
}
