package org.vas.store.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.vas.product.details.core.domain.ProductCategory;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(ProductsCatalogResource.class)
public class ProductsCatalogResourceIntegrationTest {

    @Test
    void testGetProductCatalog() {
        var productCategoriesCount = ProductCategory.count();
        given()
                .when().get("").then()
                .statusCode(200)
                .body("size()", is((int) productCategoriesCount))
                // Books
                .body("find { it.id == 1 }.name", is("Books"))
                .body("find { it.id == 1 }.products.size()", is(2))
                .body("find { it.id == 1 }.products.find { it.id == 51 }.sku", is("00000002"))
                .body("find { it.id == 1 }.products.find { it.id == 51 }.name", is("Clean Code"))
                .body("find { it.id == 1 }.products.find { it.id == 51 }.price", is(37.99f))
                .body("find { it.id == 1 }.products.find { it.id == 51 }.description",
                        is("A Handbook of Agile Software Craftsmanship"))
                .body("find { it.id == 1 }.products.find { it.id == 1 }.sku", is("00000001"))
                .body("find { it.id == 1 }.products.find { it.id == 1 }.name", is("The Pragmatic Programmer"))
                .body("find { it.id == 1 }.products.find { it.id == 1 }.price", is(29.99f))
                .body("find { it.id == 1 }.products.find { it.id == 1 }.description", is("Your journey to mastery"))
                // Electronics
                .body("find { it.id == 51 }.name", is("Electronics"))
                .body("find { it.id == 51 }.products.size()", is(2))
                .body("find { it.id == 51 }.products.find { it.id == 151 }.sku", is("00000004"))
                .body("find { it.id == 51 }.products.find { it.id == 151 }.name", is("iPhone 12"))
                .body("find { it.id == 51 }.products.find { it.id == 151 }.price", is(799.99f))
                .body("find { it.id == 51 }.products.find { it.id == 151 }.description", is("Blast past fast"))
                .body("find { it.id == 51 }.products.find { it.id == 101 }.sku", is("00000003"))
                .body("find { it.id == 51 }.products.find { it.id == 101 }.name", is("MacBook Pro"))
                .body("find { it.id == 51 }.products.find { it.id == 101 }.price", is(1299.99f))
                .body("find { it.id == 51 }.products.find { it.id == 101 }.description",
                        is("The ultimate pro notebook"))
                // Home & Kitchen
                .body("find { it.id == 101 }.name", is("Clothing"))
                .body("find { it.id == 101 }.products.size()", is(2))
                .body("find { it.id == 101 }.products.find { it.id == 251 }.sku", is("00000006"))
                .body("find { it.id == 101 }.products.find { it.id == 251 }.name", is("Nike Air Max 270"))
                .body("find { it.id == 101 }.products.find { it.id == 251 }.price", is(150.00f))
                .body("find { it.id == 101 }.products.find { it.id == 251 }.description",
                        is("The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93"))
                .body("find { it.id == 101 }.products.find { it.id == 201 }.sku", is("00000005"))
                .body("find { it.id == 101 }.products.find { it.id == 201 }.name", is("Levi's 501 Original Fit Jeans"))
                .body("find { it.id == 101 }.products.find { it.id == 201 }.price", is(59.99f))
                .body("find { it.id == 101 }.products.find { it.id == 201 }.description",
                        is("The original blue jean since 1873"))
                // Home & Kitchen
                .body("find { it.id == 151 }.name", is("Home & Kitchen"))
                .body("find { it.id == 151 }.products.size()", is(2))
                .body("find { it.id == 151 }.products.find { it.id == 301 }.sku", is("00000007"))
                .body("find { it.id == 151 }.products.find { it.id == 301 }.name",
                        is("Instant Pot Duo 7-in-1 Electric Pressure Cooker"))
                .body("find { it.id == 151 }.products.find { it.id == 301 }.price", is(79.95f))
                .body("find { it.id == 151 }.products.find { it.id == 301 }.description", is("The best-selling model"))
                .body("find { it.id == 151 }.products.find { it.id == 351 }.sku", is("00000008"))
                .body("find { it.id == 151 }.products.find { it.id == 351 }.name", is("Keurig K-Classic Coffee Maker"))
                .body("find { it.id == 151 }.products.find { it.id == 351 }.price", is(79.99f))
                .body("find { it.id == 151 }.products.find { it.id == 351 }.description",
                        is("Brews multiple K-Cup Pod sizes"));
    }

}
