package webshop.service.models;

import java.time.LocalDateTime;

import static webshop.service.filters.Constants.AUTHENTICATION_SCHEME;

public class AuthenticationData {

    public AuthenticationData(String access_token, UserRole scope, LocalDateTime created) {
        this.access_token = access_token;
        this.scope = scope;
        token_type = AUTHENTICATION_SCHEME;
        this.created = created.toString();
    }

    private String access_token;
    private String token_type;
    private UserRole scope;
    private String created;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public UserRole getScope() {
        return scope;
    }

    public void setScope(UserRole scope) {
        this.scope = scope;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
