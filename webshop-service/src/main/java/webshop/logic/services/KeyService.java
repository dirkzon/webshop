package webshop.logic.services;

import webshop.logic.interfaces.IKeyService;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyService implements IKeyService {


    @Override
    public SecretKey getSigningKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        return keyGen.generateKey();
    }
}
