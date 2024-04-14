package org.vas.product.catalog.presentation.web.http.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.vas.product.catalog.core.adapters.ProductCategoryRepository;
import org.vas.product.catalog.core.adapters.ProductRepository;
import org.vas.product.catalog.core.domain.Product;
import org.vas.product.catalog.core.domain.ProductCategory;
import org.vas.product.catalog.presentation.dtos.CreateProductDTO;
import org.vas.product.catalog.presentation.dtos.UpdateProductDTO;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
@TestHTTPEndpoint(ProductsController.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductsControllerIntegrationTest {
    @InjectSpy
    private ProductRepository productRepository;
    @Inject
    private ProductCategoryRepository productCategoryRepository;
    private static Product productToBeUpdated;

    @BeforeAll
    @Transactional
    public static void setup() {
        ProductCategory category = new ProductCategory("Test");
        category.persist();
        productToBeUpdated = new Product("42342342", "To be updated",
                "Product to be update during patch test", category);
        productToBeUpdated.persist();
    }

    @Test
    @Order(1)
    void testGetProducts() {
        var productsCount = Product.count();

        given().when().get("").then().statusCode(200).body("size()", is((int) productsCount))
                .body("[0].id", CoreMatchers.any(Integer.class)).body("[0].sku", is("00000002"))
                .body("[0].name", is("Clean Code"))
                .body("[0].description", is("A Handbook of Agile Software Craftsmanship"))
                .body("[0].category.id", is(1)).body("[0].category.name", is("Books"))
                .body("[1].id", CoreMatchers.any(Integer.class)).body("[1].sku", is("00000007"))
                .body("[1].name", is("Instant Pot Duo 7-in-1 Electric Pressure Cooker"))
                .body("[1].description", is("The best-selling model"))
                .body("[1].category.id", is(151)).body("[1].category.name", is("Home & Kitchen"))
                .body("[2].id", CoreMatchers.any(Integer.class)).body("[2].sku", is("00000004"))
                .body("[2].name", is("iPhone 12")).body("[2].description", is("Blast past fast"))
                .body("[2].category.id", is(51)).body("[2].category.name", is("Electronics"))
                .body("[3].id", CoreMatchers.any(Integer.class)).body("[3].sku", is("00000008"))
                .body("[3].name", is("Keurig K-Classic Coffee Maker"))
                .body("[3].description", is("Brews multiple K-Cup Pod sizes"))
                .body("[3].category.id", is(151)).body("[3].category.name", is("Home & Kitchen"))
                .body("[4].id", CoreMatchers.any(Integer.class)).body("[4].sku", is("00000005"))
                .body("[4].name", is("Levi's 501 Original Fit Jeans"))
                .body("[4].description", is("The original blue jean since 1873"))
                .body("[4].category.id", is(101)).body("[4].category.name", is("Clothing"))
                .body("[5].id", CoreMatchers.any(Integer.class)).body("[5].sku", is("00000003"))
                .body("[5].name", is("MacBook Pro"))
                .body("[5].description", is("The ultimate pro notebook"))
                .body("[5].category.id", is(51)).body("[5].category.name", is("Electronics"))
                .body("[6].id", CoreMatchers.any(Integer.class)).body("[6].sku", is("00000006"))
                .body("[6].name", is("Nike Air Max 270"))
                .body("[6].description", is(
                        "The Nike Air Max 270 is inspired by two icons of big Air: the Air Max 180 and Air Max 93"))
                .body("[6].category.id", is(101)).body("[6].category.name", is("Clothing"))
                .body("[7].id", CoreMatchers.any(Integer.class)).body("[7].sku", is("00000011"))
                .body("[7].name", is("Samsung Galaxy Buds Pro"))
                .body("[7].description", is("The ultimate earbuds")).body("[7].category.id", is(51))
                .body("[7].category.name", is("Electronics"))
                .body("[8].id", CoreMatchers.any(Integer.class)).body("[8].sku", is("00000009"))
                .body("[8].name", is("Samsung Galaxy S21 Ultra"))
                .body("[8].description", is("The ultimate smartphone"))
                .body("[8].category.id", is(51)).body("[8].category.name", is("Electronics"))
                .body("[9].id", CoreMatchers.any(Integer.class)).body("[9].sku", is("00000010"))
                .body("[9].name", is("Samsung Galaxy Watch 3"))
                .body("[9].description", is("The most advanced smartwatch"))
                .body("[9].category.id", is(51)).body("[9].category.name", is("Electronics"))
                .body("[10].id", CoreMatchers.any(Integer.class)).body("[10].sku", is("00000001"))
                .body("[10].name", is("The Pragmatic Programmer"))
                .body("[10].description", is("Your journey to mastery"))
                .body("[10].category.id", is(1)).body("[10].category.name", is("Books"));

        verify(productRepository, times(1)).findAllProducts();
    }

    @Test
    void testGetProductById() {
        String responseBody = given().when().get("/351").then().statusCode(200).body("id", is(351))
                .body("name", is("Keurig K-Classic Coffee Maker"))
                .body("description", is("Brews multiple K-Cup Pod sizes"))
                .body("category.id", is(151)).body("category.name", is("Home & Kitchen")).extract()
                .asString();

        assertEquals(
                "{\"id\":351,\"sku\":\"00000008\",\"name\":\"Keurig K-Classic Coffee Maker\",\"description\":\"Brews multiple K-Cup Pod sizes\",\"category\":{\"id\":151,\"name\":\"Home & Kitchen\"}}",
                responseBody);

        verify(productRepository, times(1)).findProductById(351l);
    }

    @Test
    void testGetProductByIdShouldReturnNotFound() {
        given().when().get("/453453").then().statusCode(404).body(is(""));

        verify(productRepository, times(1)).findProductById(453453l);
    }

    @Test
    void testGetProductByIdAsStringShouldReturnError() {
        given().when().get("/aaaa").then().statusCode(404);

        verify(productRepository, times(0)).findProductById(any());
    }

    @Test
    @TestTransaction
    void testCreateProduct() {
        ProductCategory category =
                productCategoryRepository.findAllProductCategories().iterator().next();
        CreateProductDTO createProductDTO =
                new CreateProductDTO("00000009", "New Product", "New Description", category.id);
        Product product = given().header("Content-type", "application/json").body(createProductDTO)
                .when().post("").then().statusCode(201)
                .body("id", is(CoreMatchers.any(Integer.class))).body("sku", is("00000009"))
                .body("name", is("New Product")).body("description", is("New Description"))
                .body("category.id", is(Long.valueOf(category.id).intValue()))
                .body("category.name", is(category.getName())).extract().as(Product.class);

        var newProduct = productRepository.findProductById(product.id).get();
        assertEquals("00000009", newProduct.getSku());
        assertEquals("New Product", newProduct.getName());
        assertEquals("New Description", newProduct.getDescription());
        assertEquals(category.id, newProduct.getCategory().getId());
        assertEquals(category.getName(), newProduct.getCategory().getName());

        verify(productRepository, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        UpdateProductDTO updateProductDTO = new UpdateProductDTO(productToBeUpdated.getId(),
                "Updated Product", "Updated Description", productToBeUpdated.getCategory().id);
        given().header("Content-type", "application/json").body(updateProductDTO).when()
                .patch("/" + productToBeUpdated.getId()).then().statusCode(202).body(is(""));

        verify(productRepository, times(1)).updateProduct(any(Product.class));
    }
}
