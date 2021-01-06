package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Account;

public interface IAccountService {
    Account isAccountValid(String details, String password)throws NullPointerException, NotFoundException;
    String createToken(Account account);
}
