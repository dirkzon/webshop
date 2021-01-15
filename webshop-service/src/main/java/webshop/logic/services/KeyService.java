package webshop.logic.services;

import webshop.logic.interfaces.IKeyService;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyService implements IKeyService {


    @Override
    public SecretKey getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode("c3VwZXJzZWNyZXRrZXk");
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
