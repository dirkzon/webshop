package webshop.logic.interfaces;

import javax.crypto.SecretKey;

public interface IKeyService {
    SecretKey getSigningKey();
}
