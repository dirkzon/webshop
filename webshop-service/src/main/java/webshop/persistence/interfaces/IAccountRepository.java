package webshop.persistence.interfaces;

import webshop.service.models.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> getAccountByDetails(String details);
}
