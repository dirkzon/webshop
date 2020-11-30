package webshop.logic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import webshop.logic.interfaces.IAccountService;
import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import java.util.Date;
import java.util.List;

public class AccountService implements IAccountService {

    @Context
    private IAccountRepository repository;

    @Inject
    public AccountService(IAccountRepository repository){
        this.repository = repository;
    }

    public Account isAccountValid(String details, String password){
        if(details.isEmpty() && password.isEmpty()) return null;
        List<Account> accounts = repository.getAccountByDetails(details);
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
                .claim("role", account.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                .compact();
    }
}
