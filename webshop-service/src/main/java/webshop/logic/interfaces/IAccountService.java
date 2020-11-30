package webshop.logic.interfaces;

import webshop.service.models.Account;

public interface IAccountService {
    Account isAccountValid(String details, String password);
    public String createToken(Account account);
}
