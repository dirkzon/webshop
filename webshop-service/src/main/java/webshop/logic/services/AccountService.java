package webshop.logic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import webshop.logic.interfaces.IAccountService;
import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static webshop.service.filters.Constants.USER_ROLE;

public class AccountService implements IAccountService {

    private final IAccountRepository repository;

    @Inject
    public AccountService(IAccountRepository repository){
        this.repository = repository;
    }

    public Account isAccountValid(String details, String password){
        if(details.isEmpty() && password.isEmpty()) return null;
        List<Account> accounts = repository.getAccountByDetails(details);
        if(accounts.isEmpty()) return null;
        for(Account account : accounts){
            if(account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }

    public String createToken(Account account){
        int userId = repository.getUserIdFromAccountId(account.getId(), account.getRole());
        return Jwts.builder()
                .setSubject(account.getUsername())
                .setId(Integer.toString(userId))
                .claim(USER_ROLE, account.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "eW91IGdvdCB0aGlzIQ==")
                .compact();
    }
}
