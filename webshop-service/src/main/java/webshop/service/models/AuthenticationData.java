package webshop.service.models;


import static webshop.service.filters.Constants.AUTHENTICATION_SCHEME;

public class AuthenticationData {

    public AuthenticationData(String access_token, UserRole scope) {
        this.access_token = access_token;
        this.scope = scope;
        token_type = AUTHENTICATION_SCHEME;
    }

    public String access_token;
    public String token_type;
    public UserRole scope;
}
