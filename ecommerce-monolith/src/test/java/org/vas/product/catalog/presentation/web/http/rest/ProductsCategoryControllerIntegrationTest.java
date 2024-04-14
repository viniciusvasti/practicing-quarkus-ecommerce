package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.vas.product.details.core.adapters.ProductCategoryRepository;
import org.vas.product.details.core.domain.ProductCategory;
import org.vas.product.details.presentation.dtos.CreateProductCategoryDTO;
import org.vas.product.details.presentation.dtos.UpdateProductCategoryDTO;
import org.vas.product.details.presentation.web.http.rest.ProductsCategoryController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

@QuarkusTest
@TestHTTPEndpoint(ProductsCategoryController.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductsCategoryControllerIntegrationTest {
    @InjectSpy
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @Order(1)
    void testGetProductCategories() {
        var categoriesCount = ProductCategory.count();

        given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is((int) categoriesCount))
                .body("[0].name", is("Books"))
                .body("[1].id", is(101))
                .body("[1].name", is("Clothing"))
                .body("[2].id", is(51))
                .body("[2].name", is("Electronics"))
                .body("[3].id", is(151))
                .body("[3].name", is("Home & Kitchen"));

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
                .when().get("/1000")
                .then()
                .statusCode(404)
                .body(is(""));

        verify(productCategoryRepository, times(1)).findProductCategoryById(1000l);
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
        ProductCategory category = given()
                .header("Content-type", "application/json")
                .body(createProductCategoryDTO)
                .when().post("")
                .then()
                .statusCode(201)
                .body("name", is("New Category"))
                .body("id", is(CoreMatchers.any(Integer.class)))
                .extract().as(ProductCategory.class);

        var newProductCategory = productCategoryRepository.findProductCategoryById(category.id).get();
        assertEquals("New Category", newProductCategory.getName());

        verify(productCategoryRepository, times(1)).saveProductCategory(newProductCategory);
    }

    @Test
    @TestTransaction
    void testUpdateProductCategory() {
        ProductCategory newCategory = new ProductCategory("Updated Category");
        newCategory = productCategoryRepository.saveProductCategory(newCategory);
        ProductCategory.flush();

        UpdateProductCategoryDTO updateProductCategoryDTO = new UpdateProductCategoryDTO(newCategory.id,
                "Updated Category");
        given()
                .header("Content-type", "application/json")
                .body(updateProductCategoryDTO)
                .when().patch("/" + newCategory.id)
                .then()
                .statusCode(202)
                .body(is(""));

        var updatedProductCategory = productCategoryRepository.findProductCategoryById(newCategory.id).get();
        assertEquals("Updated Category", updatedProductCategory.getName());

        verify(productCategoryRepository, times(1)).updateProductCategory(updatedProductCategory);
    }
}
