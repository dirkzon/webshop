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
    static void setUpAccountServiceMock(){
        repository = mock(IAccountRepository.class);
        service = new AccountService(repository);

        List<Account> accounts = new ArrayList<>();

        Account a1 = new Account("peter", "abcd", UserRole.Customer, LocalDate.parse("2017-11-26"));
        a1.setId(1);
        accounts.add(a1);
        Account a2 = new Account("henk", "password", UserRole.Customer, LocalDate.parse("2019-03-21"));
        a2.setId(2);
        accounts.add(a2);
        Account a3 = new Account("john", "1234", UserRole.Retailer, LocalDate.parse("2020-05-30"));
        a3.setId(3);
        accounts.add(a3);

        when(repository.getAccountByDetails("peter")).thenReturn(new ArrayList<>(){{add(accounts.get(0));}});
    }

    @Test
    void isAccountValidShouldSucceed() {
        //arrange

        //act
        var account = service.isAccountValid("peter", "abcd");
        //assert
        assertEquals(account.getId(), 1);
    }

    @Test
    void isAccountValidWithoutUserDetailsShouldSucceed() {
        //arrange

        //act
        var account = service.isAccountValid("", "abcd");
        //assert
        assertEquals(account, null);
    }

    @Test
    void isAccountValidWithoutPasswordDetailsShouldSucceed() {
        //arrange

        //act
        var account = service.isAccountValid("peter", "");
        //assert
        assertEquals(account, null);
    }

    @Test
    void isAccountValidWithoutPasswordAndDetailsDetailsShouldSucceed() {
        //arrange

        //act
        var account = service.isAccountValid("", "");
        //assert
        assertEquals(account, null);
    }
}