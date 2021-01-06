package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Account;

public interface IAccountService {
    Account isAccountValid(String details, String password)throws NotFoundException;
    String createToken(Account account);
}
