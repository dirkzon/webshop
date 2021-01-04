package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.IAccountService;
import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;
import webshop.service.models.UserRole;

import javax.ws.rs.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTests {

    @Context
    static IAccountRepository repository;
    static IAccountService service;

    @BeforeAll
    static void setUpAccountServiceMock() throws Exception {
        repository = mock(IAccountRepository.class);
        service = new AccountService(repository);

        List<Account> accounts = new ArrayList<>();

        Account a1 = new Account("peter","peter@mail.nl" , "abcd", UserRole.CUSTOMER, LocalDate.parse("2017-11-26"));
        a1.setId(1);
        accounts.add(a1);
        Account a2 = new Account("henk","henk@gmail.com", "password", UserRole.CUSTOMER, LocalDate.parse("2019-03-21"));
        a2.setId(2);
        accounts.add(a2);
        Account a3 = new Account("john", "john@outlook.com","1234", UserRole.RETAILER, LocalDate.parse("2020-05-30"));
        a3.setId(3);
        accounts.add(a3);

        when(repository.getAccountByDetails("peter")).thenReturn(new ArrayList<>(){{add(accounts.get(0));}});
    }

    @Test
    void isAccountValidShouldSucceed() throws Exception {
        //arrange

        //act
        var account = service.isAccountValid("peter", "abcd");
        //assert
        assertEquals(1, account.getId());
    }

    @Test
    void isAccountValidWithoutUserDetailsShouldNotSucceed(){
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.isAccountValid("", "abcd"));
        assertThrows(Exception.class, () ->
                service.isAccountValid(null, "abcd"));
    }

    @Test
    void isAccountValidWithoutPasswordDetailsShouldNotSucceed(){
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.isAccountValid("peter", ""));
        assertThrows(Exception.class, () ->
                service.isAccountValid("peter", null));
    }


    @Test
    void isAccountValidWithoutPasswordAndDetailsDetailsShouldSucceed() {
        //arrange

        //act
        //assert
        assertThrows(Exception.class, () ->
                service.isAccountValid("", ""));
        assertThrows(Exception.class, () ->
                service.isAccountValid(null, null));
    }
}
