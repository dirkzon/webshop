package webshop.logic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import webshop.logic.interfaces.IAccountService;
import webshop.logic.interfaces.IKeyService;
import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static webshop.service.filters.Constants.USER_ROLE;

public class AccountService implements IAccountService {

    private final IAccountRepository repository;
    private final IKeyService keyStore;

    @Inject
    public AccountService(IAccountRepository repository, IKeyService keyService) {
        this.repository = repository;
        this.keyStore = keyService;
    }

    public Account isAccountValid(String details, String password) throws NotFoundException {
        if (details.isEmpty() && password.isEmpty()) throw new NullPointerException("Credentials missing");
        List<Account> accounts = repository.getAccountByDetails(details);
        if (accounts.isEmpty()) throw new NotFoundException("Account not found");
        for (Account account : accounts) {
            if (account.getPassword().equals(password)) {
                return account;
            }
        }
        throw new NotFoundException("Account not found");
    }

    public String createToken(Account account) {
        SecretKey signingKey = keyStore.getSigningKey();
        int id = repository.getUserIdFromAccountId(account.getId(), account.getRole());
        Logger.getGlobal().fine(String.format("User with id %d has logged in", account.getId()));
        return Jwts.builder()
                .setSubject(account.getUsername())
                .setId(Integer.toString(id))
                .claim(USER_ROLE, account.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }
}
