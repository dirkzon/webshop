package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Account;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface IAccountService {
    Account isAccountValid(String details, String password)throws NotFoundException;
    String createToken(Account account) throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
