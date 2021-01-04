package webshop.logic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import webshop.logic.interfaces.IAccountService;
import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static webshop.service.filters.Constants.USER_ROLE;

public class AccountService implements IAccountService {

    private final IAccountRepository repository;

    @Inject
    public AccountService(IAccountRepository repository){
        this.repository = repository;
    }

    public Account isAccountValid(String details, String password)throws Exception{
        try{
            if(details.isEmpty() || details == null
                    && password.isEmpty() || password == null) throw new NullPointerException("Credentials missing");
            List<Account> accounts = repository.getAccountByDetails(details);
            if(accounts.isEmpty()) throw new NotFoundException("Account not found");
            for(Account account : accounts){
                if(account.getPassword().equals(password)){
                    return account;
                }
            }
            throw new NotFoundException("Account not found");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String createToken(Account account)throws Exception{
        try{
            int id = repository.getUserIdFromAccountId(account.getId(), account.getRole());
            Logger.getGlobal().log(Level.INFO, "User with id: " + account.getId() + " has logged in.");
            return Jwts.builder()
                    .setSubject(account.getUsername())
                    .setId(Integer.toString(id))
                    .claim(USER_ROLE, account.getRole())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "eW91IGdvdCB0aGlzIQ==")
                    .compact();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
