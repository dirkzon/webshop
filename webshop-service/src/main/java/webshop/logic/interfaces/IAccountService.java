package webshop.logic.interfaces;

import webshop.service.models.Account;

public interface IAccountService {
    Account isAccountValid(String details, String password)throws Exception;
    String createToken(Account account)throws Exception;
}
