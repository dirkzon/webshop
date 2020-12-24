package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.IRetailerService;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.*;

import javax.ws.rs.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class RetailerServiceTests {

    @Context
    static IRetailerRepository repository;
    static IRetailerService service;

    @BeforeAll
    static void setUpRetailerServiceMock()throws Exception{
        repository = mock(IRetailerRepository.class);
        service = new RetailerService(repository);

        List<Retailer> retailers = new ArrayList<>();

        Image image = new Image("testurl");

        Retailer retailer1 = new Retailer(0, new Account("john", "abcd","mail",  UserRole.Retailer, LocalDate.parse("2018-10-02")),image );
        retailers.add(retailer1);

        List<Product> products = new ArrayList<>();
        products.add(new Product(0, "tv", 200.0, "description tv", LocalDate.parse("2020-01-08"),retailer1,2.5, image));
        products.add(new Product(1, "mouse", 15.50, "computer mouse", LocalDate.parse("2020-11-29"),retailer1,4.5, image));

        when(repository.getRetailerById(0)).thenReturn(retailers.get(0));
        when(repository.saveRetailer(any(Retailer.class))).thenReturn(retailer1);
        when(repository.updateRetailerById(anyInt(), any(Retailer.class))).thenReturn(retailers.get(0));
        when(repository.getProductsInCatalog(0)).thenReturn(products);
        when(repository.createNewProductInCatalog(any(int.class), any(Product.class))).thenReturn(products.get(0));
    }

    @Test
    void getRetailerByIdShouldSucceed() throws Exception{
        //arrange

        //act
        Retailer retailer = service.getRetailerById(0);
        //assert
        assertEquals(0, retailer.getId());
    }

    @Test
    void getRetailerByIdWithNegativeIdShouldSucceed() throws Exception{
        //arrange

        //act
        Retailer retailer = service.getRetailerById(-3);
        //assert
        assertNull(retailer);
    }

    @Test
    void saveRetailerShouldSucceed() throws Exception{
        //arrange
        Retailer newRetailer = new Retailer();
        newRetailer.setAccount(new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")));
        newRetailer.setAvatar(new Image("testurl"));
        //act
        Retailer retailer = service.saveRetailer(newRetailer);
        //assert
        assertEquals(0, retailer.getId());
    }

    @Test
    void saveRetailerMissingAccountShouldNotSucceed() throws Exception{
        //arrange
        Retailer newRetailer = new Retailer();
        newRetailer.setAvatar(new Image("testurl"));
        //act
        Retailer retailer = service.saveRetailer(newRetailer);
        //assert
        assertNull(retailer);
    }

    @Test
    void saveRetailerMissingAvatarShouldNotSucceed() throws Exception{
        //arrange
        Retailer newRetailer = new Retailer();
        newRetailer.setAccount(new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")));
        //act
        Retailer retailer = service.saveRetailer(newRetailer);
        //assert
        assertNull(retailer);
    }

    @Test
    void updateRetailerShouldSucceed() throws Exception{
        //arrange
        Retailer updatedRetailer = new Retailer();
        updatedRetailer.setAccount(new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")));
        updatedRetailer.setAvatar(new Image("testurl"));
        updatedRetailer.setId(0);
        //act
        Retailer retailer = service.updateRetailerById(0,updatedRetailer);
        //assert
        assertEquals(updatedRetailer.getId(), retailer.getId());
    }

    @Test
    void updateRetailerMissingAccountShouldNotSucceed() throws Exception{
        //arrange
        Retailer updatedRetailer = new Retailer();
        updatedRetailer.setAvatar(new Image("testurl"));
        updatedRetailer.setId(0);
        //act
        Retailer retailer = service.updateRetailerById(0,updatedRetailer);
        //assert
        assertNull(retailer);
    }

    @Test
    void updateRetailerMissingAvatarShouldNotSucceed() throws Exception{
        //arrange
        Retailer updatedRetailer = new Retailer();
        updatedRetailer.setAccount(new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")));
        updatedRetailer.setId(0);
        //act
        Retailer retailer = service.updateRetailerById(0,updatedRetailer);
        //assert
        assertNull(retailer);
    }

    @Test
    void updateRetailerWithNegativeIdShouldNotSucceed() throws Exception{
        //arrange
        Retailer updatedRetailer = new Retailer();
        updatedRetailer.setAccount(new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")));
        updatedRetailer.setAvatar(new Image("testurl"));
        updatedRetailer.setId(-2);
        //act
        Retailer retailer = service.updateRetailerById(0,updatedRetailer);
        //assert
        assertNull(retailer);
    }

    @Test
    void getAllProductsInCatalogShouldSucceed() throws Exception{
        //arrange
        List<Product> expected = new ArrayList<>();
        Image image = new Image("testurl");
        Retailer retailer = new Retailer(0, new Account("john", "abcd","mail", UserRole.Retailer, LocalDate.parse("2018-10-02")),image );
        expected.add(new Product(0, "tv", 200.0, "description tv", LocalDate.parse("2020-01-08"),retailer,2.5, image));
        expected.add(new Product(1, "mouse", 15.50, "computer mouse", LocalDate.parse("2020-11-29"),retailer,4.5, image));
        //act
        List<Product> actual = service.getAllProductsInCatalog(0);
        //assert
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(1).getId(), actual.get(1).getId());
    }

    @Test
    void createNewProduct() throws Exception{
        //arrange
        Product newProduct = new Product();
        newProduct.setDescription("description tv");
        newProduct.setImage(new Image("testurl"));
        newProduct.setName("tv");
        newProduct.setPrice(200.0);
        newProduct.setId(0);
        //act
        Product product = service.createNewProduct(0, newProduct);
        //assert
        assertEquals(newProduct.getId(), product.getId());
    }

    @Test
    void createNewProductMissingDescriptionShouldNotSucceed() throws Exception{
        //arrange
        Product newProduct = new Product();
        newProduct.setImage(new Image("testurl"));
        newProduct.setName("tv");
        newProduct.setPrice(200.0);
        newProduct.setId(0);
        //act
        Product product = service.createNewProduct(0, newProduct);
        //assert
        assertNull(product);
    }

    @Test
    void createNewProductMissingNameShouldNotSucceed() throws Exception{
        //arrange
        Product newProduct = new Product();
        newProduct.setImage(new Image("testurl"));
        newProduct.setDescription("description tv");
        newProduct.setPrice(200.0);
        newProduct.setId(0);
        //act
        Product product = service.createNewProduct(0, newProduct);
        //assert
        assertNull(product);
    }

    @Test
    void createNewProductMissingPriceShouldNotSucceed() throws Exception{
        //arrange
        Product newProduct = new Product();
        newProduct.setImage(new Image("testurl"));
        newProduct.setName("tv");
        newProduct.setDescription("description tv");
        newProduct.setId(0);
        //act
        Product product = service.createNewProduct(0, newProduct);
        //assert
        assertNull(product);
    }

    @Test
    void createNewProductMissingImageShouldNotSucceed()throws Exception {
        //arrange
        Product newProduct = new Product();
        newProduct.setPrice(200.0);
        newProduct.setName("tv");
        newProduct.setDescription("description tv");
        newProduct.setId(0);
        //act
        Product product = service.createNewProduct(0, newProduct);
        //assert
        assertNull(product);
    }
}