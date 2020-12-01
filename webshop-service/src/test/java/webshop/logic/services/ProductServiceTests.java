package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.*;

import javax.ws.rs.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTests {

    @Context
    static IProductRepository repository;
    static IProductService service;

    @BeforeAll
    static void setUpProductServiceMock(){
        repository = mock(IProductRepository.class);
        service = new ProductService(repository);

        List<Product> products = new ArrayList<>();

        Image image = new Image("testurl");

        Retailer retailer1 = new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),image );

        Customer customer = new Customer(0, new Account("henk", "1234", UserRole.Customer, LocalDate.parse("2018-10-02")),
                new Address("NL", "street", 15), image);

        products.add(new Product(1, "tv", 200.0, "description tv", LocalDate.parse("2020-01-08"),retailer1,2.5, image));
        products.add(new Product(2, "mouse", 15.50, "computer mouse", LocalDate.parse("2020-11-29"),retailer1,4.5, image));

        Review review = new Review(4.5, "review body", LocalDate.parse("2018-10-02"), customer, products.get(0));
        review.setId(1);

        when(repository.getProductById(1)).thenReturn(products.get(1));
        when(repository.updateProductById(anyInt(), any(Product.class))).thenReturn(products.get(0));
        when(repository.createReviewOnProductById(anyInt(), any(Review.class))).thenReturn(review);
        when(repository.browseProducts(any(BrowseVars.class))).thenReturn(products);
    }

    @Test
    void getProductByIdShouldSucceed() {
        //arrange

        //act
        Product product = service.getProductById(1);
        //assert
        assertEquals(2, product.getId());
    }

    @Test
    void getProductByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act
        Product product = service.getProductById(-3);
        //assert
        assertNull(product);
    }

    @Test
    void removeProductByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act
        var bool = service.removeProductById(-2);
        //assert
        assertFalse(bool);
    }

    @Test
    void updateProductShouldSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act
       Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertEquals(updatedProduct.getId(), product.getId());
    }

    @Test
    void updateProductWithoutIdImageShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act
        Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertNull(product);
    }

    @Test
    void updateProductWithoutDescriptionShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act
       Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertNull(product);
    }

    @Test
    void updateProductWithoutNameShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setPrice(10.50);

        //act
       updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));
        Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertNull(product);
    }

    @Test
    void updateProductWithoutPriceShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act
        Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertNull(product);
    }

    @Test
    void updateProductWithoutRetailerShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        //act

        Product product = service.updateProductById(1, updatedProduct);
        //assert
        assertNull(product);
    }

    @Test
    void createReviewOnProductShouldSucceed() {
        //arrange
        Review review = new Review(4.5, "body", LocalDate.parse("2018-10-02"), new Customer(), new Product());
        review.setId(1);
        //act

        service.createReviewOnProductById(1, review);
        //assert
        assertEquals(1, review.getId());
    }

    @Test
    void createReviewOnProductWithoutCustomerShouldNotSucceed() {
        //arrange
        Review newReview = new Review();
        newReview.setId(1);
        newReview.setBody("body");
        newReview.setRating(4.5);
        //act

        Review review = service.createReviewOnProductById(1, newReview);
        //assert
        assertNull(review);
    }

    @Test
    void createReviewOnProductWithoutBodyShouldNotSucceed() {
        //arrange
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setRating(4.5);
        //act
        Review review = service.createReviewOnProductById(1, newReview);
        //assert
        assertNull(review);
    }

    @Test
    void createReviewOnProductWithoutRatingShouldNotSucceed() {
        //arrange
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setBody("body");
        //act
        Review review = service.createReviewOnProductById(1, newReview);
        //assert
        assertNull(review);
    }

    @Test
    void createReviewOnProductWithNegativeIdsShouldNotSucceed() {
        //arrange
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setBody("body");
        newReview.setRating(4.5);
        //act

        Review review = service.createReviewOnProductById(-3, newReview);
        //assert
        assertNull(review);
    }

    @Test
    void browseProductsShouldSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = 50;
        fields.minPrice = 5;
        fields.query = "new query";
        fields.targetRating = 3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertEquals(1, products.get(0).getId());
        assertEquals(2, products.get(1).getId());
    }

    @Test
    void browseProductsWithNegativeMinPriceShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = 50;
        fields.minPrice = -5;
        fields.query = "new query";
        fields.targetRating = 3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertNull(products);
    }

    @Test
    void browseProductsWithNegativeMaxPriceShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = -50;
        fields.minPrice = 5;
        fields.query = "new query";
        fields.targetRating = 3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertNull(products);
    }

    @Test
    void browseProductsWithMinPriceGreaterThanMaxPriceShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = 50;
        fields.minPrice = 100;
        fields.query = "new query";
        fields.targetRating = 3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertNull(products);
    }

    @Test
    void browseProductsWithNegativeTargetRatingShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = 50;
        fields.minPrice = 5;
        fields.query = "new query";
        fields.targetRating = -3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertNull(products);
    }

    @Test
    void browseProductsWithoutQueryShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.maxPrice = 50;
        fields.minPrice = 5;
        fields.targetRating = -3.5;
        //act
        List<Product> products = service.browseProducts(fields);
        //assert
        assertNull(products);
    }
}