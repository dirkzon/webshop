package webshop.logic.services;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class KeyService {

    private static final String PASSWORD = "password";

    public static SecretKey GetKeyFromStore(String alias, String location) throws KeyStoreException, IOException,
            NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        InputStream readStream = new FileInputStream(location);
        keyStore.load(readStream, PASSWORD.toCharArray());
        return (SecretKey) keyStore.getKey(alias, PASSWORD.toCharArray());
    }
}
