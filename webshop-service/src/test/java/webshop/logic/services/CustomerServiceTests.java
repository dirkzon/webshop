package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.*;

import javax.ws.rs.core.Context;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTests {

    @Context
    static ICustomerRepository repository;
    static ICustomerService service;

    @BeforeAll
    static void setUpCustomerServiceMock(){
        repository = mock(ICustomerRepository.class);
        service = new CustomerService(repository);

        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer(0, new Account("henk", "1234", UserRole.CUSTOMER, LocalDate.parse("2018-10-02")),
                new Address("NL", "street", 15),
                new Image("testurl")));
        customers.add(new Customer(1, new Account("peter", "abcd", UserRole.CUSTOMER, LocalDate.parse("2016-08-16")),
                new Address("GB", "otherstreet", 7),
                new Image("testurl")));
        customers.add(new Customer(2, new Account("john", "pass", UserRole.CUSTOMER, LocalDate.parse("2020-01-05")),
                new Address("DE", "thatstreet", 42),
                new Image("testurl")));

        Customer testCustomer = new Customer(3, new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")),
                new Address("AU", "road", 24),
                new Image("testurl"));

        when(repository.getCustomerById(0)).thenReturn(customers.get(0));
        when(repository.saveCustomer(any(Customer.class))).thenReturn(testCustomer);
        when(repository.updateCustomerById(anyInt(), any(Customer.class))).thenReturn(testCustomer);
    }

    //getCustomersById
    @Test
    void getCustomerByIdShouldSucceed() {
        //arrange

        //act
        Customer customer = service.getCustomerById(0);
        //assert
        assertEquals(0, customer.getId());
    }

    @Test
    void getCustomerByIdShouldNotSucceed() {
        //arrange

        //act
        Customer customer = service.getCustomerById(0);
        //assert
        assertNotEquals(2, customer.getId());
    }

    @Test
    void getCustomerByIdWithNegativeIdShouldNotSucceed() {
        //arrange

        //act
        Customer customer = service.getCustomerById(-5);
        //assert
        assertNull(customer);
    }

    //saveCustomer
    @Test
    void saveCustomerShouldSucceed(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAvatar(new Image("testurl"));
        newCustomer.setAddress(new Address("AU", "road", 24));
        newCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        //act
        Customer customer = service.saveCustomer(newCustomer);
        //assert
        assertEquals(3, customer.getId());
    }

    @Test
    void saveCustomerMissingAvatarShouldReturnnull(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAddress(new Address("AU", "road", 24));
        newCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        //act
        Customer customer = service.saveCustomer(newCustomer);
        //assert
        assertNull(customer);
    }

    @Test
    void saveCustomerMissingAddressShouldReturnnull(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAvatar(new Image("testurl"));
        newCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        //act
        Customer customer = service.saveCustomer(newCustomer);
        //assert
        assertNull(customer);
    }

    @Test
    void saveCustomerMissingAccountShouldReturnnull(){
        //arrange
        Customer newCustomer = new Customer();
        newCustomer.setAddress(new Address("AU", "road", 24));
        newCustomer.setAvatar(new Image("testurl"));
        //act
        Customer customer = service.saveCustomer(newCustomer);
        //assert
        assertNull(customer);
    }

    //updateCustomerById

    @Test
    void updateCustomerShouldSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
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
        updatedCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setAvatar(new Image("testurl"));
        updatedCustomer.setId(3);
        //act
        Customer customer = service.updateCustomerById(3, updatedCustomer);
        //assert
        assertNull(customer);
    }

    @Test
    void updateCustomerMissingAccountShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAvatar(new Image("testurl"));
        updatedCustomer.setId(3);
        //act
        Customer customer = service.updateCustomerById(3, updatedCustomer);
        //assert
        assertNull(customer);
    }

    @Test
    void updateCustomerMissingAvatarShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setId(3);
        //act
        Customer customer = service.updateCustomerById(3, updatedCustomer);
        //assert
        assertNull(customer);
    }

    @Test
    void updateCustomerWithNegativeIdShouldNotSucceed(){
        //arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setAddress(new Address("AU", "road", 24));
        updatedCustomer.setAccount(new Account("piet", "letmein", UserRole.CUSTOMER, LocalDate.parse("2015-11-29")));
        updatedCustomer.setId(-2);
        //act
        Customer customer = service.updateCustomerById(3, updatedCustomer);
        //assert
        assertNull(customer);
    }

    //removeCustomerById

    @Test
    void removeCustomerByIdWithNegativeIdShouldSucceed(){
        //arrange

        //act
        var bool = service.removeCustomerById(-4);
        //assert
        assertFalse(bool);
    }
}