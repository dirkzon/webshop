package webshop.service;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.persistence.repositories.CustomerRepository;
import webshop.persistence.repositories.RetailerRepository;
import webshop.service.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
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

            ////===++++++++++++++++++++++++++++++++++++++++++++++++++===////

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
