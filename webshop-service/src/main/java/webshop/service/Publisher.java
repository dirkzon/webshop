package webshop.service;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import javax.crypto.SecretKey;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static webshop.logic.services.KeyService.GetKey;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class Publisher {

    private static final URI BASE_URI = URI.create("http://localhost:4545/v2/");

    public static void main(String[] args) {

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();

            // create and start a grizzly server
            GrizzlyHttpServerFactory.createHttpServer(BASE_URI, customApplicationConfig, true);

        } catch (Exception ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
