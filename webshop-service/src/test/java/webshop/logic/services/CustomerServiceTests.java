package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.*;

import javax.ws.rs.core.Context;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTests {

    @Context
    static ICustomerRepository repository;
    static ICustomerService service;
    static IProductRepository productRepository;

    @BeforeAll
    static void setUpCustomerServiceMock() {
        repository = mock(ICustomerRepository.class);
        productRepository = mock(IProductRepository.class);
        service = new CustomerService(repository, productRepository);

        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer(0, new Account("henk", "1234","henk@gmail.com", UserRole.CUSTOMER, LocalDate.parse("2018-10-02")),
                new Address("NL", "street", 15),
                new Image("testurl")));
        customers.add(new Customer(1, new Account("peter", "abcd","peter@outlook.com",  UserRole.CUSTOMER, LocalDate.parse("2016-08-16")),
                new Address("GB", "otherstreet", 7),
                new Image("testurl")));
        customers.add(new Customer(2, new Account("john", "pass","mail", UserRole.CUSTOMER, LocalDate.parse("2020-01-05")),
                new Address("DE", "thatstreet", 42),
                new Image("testurl")));

        Customer testCustomer = new Customer(3, new Account("piet", "letmein","emailaddress", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")),
                new Address("AU", "road", 24),
                new Image("testurl"));

        when(repository.getCustomerById(0)).thenReturn(customers.get(0));
        when(repository.saveCustomer(any(Customer.class))).thenReturn(testCustomer);
        when(repository.updateCustomerById(anyInt(), any(Customer.class))).thenReturn(testCustomer);
    }

    //getCustomersById
    @Test
    void getCustomerByIdShouldSucceed() throws Exception {
        //arrange

        //act
        Customer customer = service.getCustomerById(0);
        //assert
        assertEquals(0, customer.getId());
    }

    @Test
    void getCustomerByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.getCustomerById(-5));
    }

    //saveCustomer
    @Test
    void saveCustomerShouldSucceed()throws Exception{
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAvatar(new Image("testurl"));
        newCustomer.setAddress(new Address("AU", "road", 24));
        newCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        //act
        Customer customer = service.saveCustomer(newCustomer);
        //assert
        assertEquals(3, customer.getId());
    }

    @Test
    void saveCustomerMissingAddressShouldNotSucceed(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAvatar(new Image("testurl"));
        newCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.saveCustomer(newCustomer));
    }

    @Test
    void saveCustomerMissingAccountShouldNotSucceed(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAddress(new Address("AU", "road", 24));
        newCustomer.setAvatar(new Image("testurl"));
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.saveCustomer(newCustomer));
    }

    //updateCustomerById

    @Test
    void updateCustomerShouldSucceed()throws Exception{
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setAvatar(new Image("testurl"));
        updatedCustomer.setId(3);
        //act
        Customer customer = service.updateCustomerById(3, updatedCustomer);
        //assert
        assertEquals(updatedCustomer.getId(), customer.getId());
    }

    @Test
    void updateCustomerMissingAddressShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setAvatar(new Image("testurl"));
        updatedCustomer.setId(3);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateCustomerById(3, updatedCustomer));
    }

    @Test
    void updateCustomerMissingAccountShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAvatar(new Image("testurl"));
        updatedCustomer.setId(3);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateCustomerById(3, updatedCustomer));
    }

    @Test
    void updateCustomerMissingAvatarShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setId(3);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateCustomerById(3, updatedCustomer));
    }

    @Test
    void updateCustomerWithNegativeIdShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein","mail", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setId(-2);
        //act

        //assert
        assertThrows(Exception.class, () ->
                service.updateCustomerById(-2, updatedCustomer));
    }
}
