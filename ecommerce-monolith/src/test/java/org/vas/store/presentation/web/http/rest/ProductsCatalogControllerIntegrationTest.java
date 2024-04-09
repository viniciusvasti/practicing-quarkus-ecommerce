package org.vas.store.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.vas.product.catalog.core.domain.ProductCategory;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(ProductsCatalogController.class)
public class ProductsCatalogControllerIntegrationTest {

    @Test
    void testGetProductCatalog() {
        var productCategoriesCount = ProductCategory.count();
        String responseBody = given()
                .when().get("")
                .then()
                .statusCode(200)
                .body("size()", is((int) productCategoriesCount))
                .extract().asString();

        assertTrue(responseBody.contains(
                "{\"id\":1,\"name\":\"Books\",\"products\":[{\"id\":51,\"sku\":\"00000002\",\"name\":\"Clean Code\",\"price\":37.99,\"description\":\"A Handbook of Agile Software Craftsmanship\",\"categoryId\":1},{\"id\":1,\"sku\":\"00000001\",\"name\":\"The Pragmatic Programmer\",\"price\":29.99,\"description\":\"Your journey to mastery\",\"categoryId\":1}]"));
        assertTrue(responseBody.contains(
                "{\"id\":101,\"name\":\"Clothing\",\"products\":[{\"id\":251,\"sku\":\"00000006\",\"name\":\"Nike Air Max 270\",\"price\":150.00,\"description\":\"The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93\",\"categoryId\":101},{\"id\":201,\"sku\":\"00000005\",\"name\":\"Levi's 501 Original Fit Jeans\",\"price\":59.99,\"description\":\"The original blue jean since 1873\",\"categoryId\":101}]"));
        assertTrue(responseBody.contains(
                "{\"id\":51,\"name\":\"Electronics\",\"products\":[{\"id\":151,\"sku\":\"00000004\",\"name\":\"iPhone 12\",\"price\":799.99,\"description\":\"Blast past fast\",\"categoryId\":51},{\"id\":101,\"sku\":\"00000003\",\"name\":\"MacBook Pro\",\"price\":1299.99,\"description\":\"The ultimate pro notebook\",\"categoryId\":51}]"));
        assertTrue(responseBody.contains(
                "{\"id\":151,\"name\":\"Home & Kitchen\",\"products\":[{\"id\":301,\"sku\":\"00000007\",\"name\":\"Instant Pot Duo 7-in-1 Electric Pressure Cooker\",\"price\":79.95,\"description\":\"The best-selling model\",\"categoryId\":151},{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"price\":79.99,\"description\":\"Brews multiple K-Cup Pod sizes\",\"categoryId\":151}]}"));
    }

}
