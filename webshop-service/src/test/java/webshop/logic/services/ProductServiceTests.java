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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTests {

    @Context
    static IProductRepository repository;
    static IProductService service;

    @BeforeAll
    static void setUpProductServiceMock()throws Exception{
        repository = mock(IProductRepository.class);
        service = new ProductService(repository);

        List<Product> products = new ArrayList<>();

        Image image = new Image("testurl");

        Retailer retailer1 = new Retailer(0, new Account("john", "abcd", "mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),image );

        Customer customer = new Customer(0, new Account("henk", "1234","mail",  UserRole.Customer, LocalDate.parse("2018-10-02")),
                new Address("NL", "street", 15), image);

        products.add(new Product(1, "tv", 200.0, "description tv", LocalDate.parse("2020-01-08"),retailer1,2.5, image));
        products.add(new Product(2, "mouse", 15.50, "computer mouse", LocalDate.parse("2020-11-29"),retailer1,4.5, image));

        Review review = new Review(4.5, "review body", LocalDate.parse("2018-10-02"), customer, products.get(0));
        review.setId(1);

        when(repository.getProductById(1)).thenReturn(products.get(0));
        when(repository.updateProductById(anyInt(), any(Product.class))).thenReturn(products.get(0));
        when(repository.createReviewOnProductById(anyInt(), any(Review.class))).thenReturn(review);
        when(repository.browseProducts(any(BrowseVars.class))).thenReturn(products);
    }

    @Test
    void getProductByIdShouldSucceed() throws Exception{
        //arrange

        //act
        Product product = service.getProductById(1);
        //assert
        assertEquals(1, product.getId());
    }

    @Test
    void getProductByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.getProductById(-3));
    }

    @Test
    void removeProductByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.removeProductById(-2));
    }

    @Test
    void updateProductShouldSucceed() throws Exception{
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

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
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateProductById(1, updatedProduct));
    }

    @Test
    void updateProductWithoutDescriptionShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setName("product name");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateProductById(1, updatedProduct));
    }

    @Test
    void updateProductWithoutNameShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setPrice(10.50);
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateProductById(1, updatedProduct));
    }

    @Test
    void updateProductWithoutPriceShouldNotSucceed() {
        //arrange
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setImage(new Image("url"));
        updatedProduct.setDescription("description");
        updatedProduct.setName("product name");
        updatedProduct.setRetailer(new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),new Image("image_url")));

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateProductById(1, updatedProduct));
    }

    @Test
    void createReviewOnProductShouldSucceed() throws Exception{
        //arrange
        Customer customer = new Customer();
        customer.setId(1);
        Product product = new Product();
        product.setId(1);
        Review review = new Review();
        review.setCustomer(customer);
        review.setProduct(product);
        review.setRating(4.5);
        review.setBody("review body");
        //act
        service.createReviewOnProductById(1, review);
        //assert
        assertEquals(0, review.getId());
    }

    @Test
    void createReviewOnProductWithoutCustomerShouldNotSucceed() {
        //arrange
        Product product = new Product();
        product.setId(1);
        Review newReview = new Review();
        newReview.setId(1);
        newReview.setBody("body");
        newReview.setRating(4.5);
        newReview.setProduct(product);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.createReviewOnProductById(1, newReview));
    }

    @Test
    void createReviewOnProductWithoutBodyShouldNotSucceed() {
        //arrange
        Product product = new Product();
        product.setId(1);
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setRating(4.5);
        newReview.setProduct(product);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.createReviewOnProductById(1, newReview));
    }

    @Test
    void createReviewOnProductWithoutRatingShouldNotSucceed(){
        //arrange
        Product product = new Product();
        product.setId(1);
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setBody("body");
        newReview.setProduct(product);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.createReviewOnProductById(1, newReview));
    }

    @Test
    void createReviewOnProductWithNegativeIdsShouldNotSucceed(){
        //arrange
        Review newReview = new Review();
        newReview.setCustomer(new Customer());
        newReview.setId(1);
        newReview.setBody("body");
        newReview.setRating(4.5);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.createReviewOnProductById(-3, newReview));
    }

    @Test
    void browseProductsShouldSucceed()throws Exception{
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.setMaxPrice(50);
        fields.setMinPrice(5);
        fields.setQuery("new query");
        fields.setMinRating(3.5);
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
        fields.setMaxPrice(50);
        fields.setMinPrice(-5);
        fields.setQuery("new query");
        fields.setMinRating(3.5);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.browseProducts(fields));
    }

    @Test
    void browseProductsWithNegativeMaxPriceShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.setMaxPrice(-50);
        fields.setMinPrice(5);
        fields.setQuery("new query");
        fields.setMinRating(3.5);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.browseProducts(fields));
    }

    @Test
    void browseProductsWithMinPriceGreaterThanMaxPriceShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.setMaxPrice(50);
        fields.setMinPrice(100);
        fields.setQuery("new query");
        fields.setMinRating(3.5);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.browseProducts(fields));
    }

    @Test
    void browseProductsWithNegativeMinRatingShouldNotSucceed(){
        //arrange
        BrowseVars fields = new BrowseVars();
        fields.setMaxPrice(50);
        fields.setMinPrice(5);
        fields.setQuery("new query");
        fields.setMinRating(-3.5);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.browseProducts(fields));
    }
}
