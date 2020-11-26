package webshop.logic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import webshop.logic.interfaces.IAuthenticationService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.AbstractUser;
import webshop.service.models.Roles;

import javax.inject.Inject;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;

public class AuthenticationService implements IAuthenticationService {

    private  ICustomerRepository customerRepository;

    private  IRetailerRepository retailerRepository;

    @Inject
    public AuthenticationService(ICustomerRepository customerRepo, IRetailerRepository retailerRepo){
        this.customerRepository = customerRepo;
        this.retailerRepository = retailerRepo;
    }

    public String ValidateUser(String token){
        final String encodedCredentials = token.replace("Bearer ", "");
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        AbstractUser user;

        user = customerRepository.IsUserValid(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                return issueToken(user, Roles.Customer);
            }else{
                return null;
            }
        }
        user = retailerRepository.IsUserValid(username);
        if(user != null) {
            if(user.getPassword().equals(password)){
                return issueToken(user, Roles.Retailer);
            }else{
                return null;
            }
        }
        return null;
    }

    private String issueToken(AbstractUser user, Roles role){
        return Jwts.builder()
                .setSubject(user.getName())
                .setId(user.getId())
                .claim("role", role)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }
}
