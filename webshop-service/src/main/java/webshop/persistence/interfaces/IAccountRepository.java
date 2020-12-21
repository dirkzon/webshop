package webshop.persistence.interfaces;

import webshop.service.models.Account;
import webshop.service.models.UserRole;

import java.util.List;

public interface IAccountRepository {
    List<Account> getAccountByDetails(String details)throws Exception;
    int getUserIdFromAccountId(int id, UserRole role)throws Exception;
}
