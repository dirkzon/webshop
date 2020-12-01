package webshop.service;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            Logger.getGlobal().log(Level.CONFIG, "Hosting resources at " + BASE_URI.toURL());

        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
