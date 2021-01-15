package webshop.logic.interfaces;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public interface IKeyService {
    SecretKey getSigningKey() throws NoSuchAlgorithmException;
}
