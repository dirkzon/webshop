package webshop.service;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
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

    private static final URI BASE_URI = URI.create("http://localhost:4545/v1/");



    public static void main(String[] args) {

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();

            // create and start a grizzly server
            GrizzlyHttpServerFactory.createHttpServer(BASE_URI, customApplicationConfig, true);

            Logger.getGlobal().log(Level.CONFIG, "Hosting resources at " + BASE_URI.toURL());

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate.sql");
            EntityManager manager = entityManagerFactory.createEntityManager();


            manager.getTransaction().begin();

            Image avatar = new Image("https://i.stack.imgur.com/i8pKs.png", 800, 800);

            Account account1 = new Account("john", "abcd", UserRole.Retailer, LocalDate.now(), avatar);
            Retailer retailer = new Retailer(account1);

            Address address = new Address( "country",  "postalCode",  "streetName", 69);

            Customer customer = new Customer(account1,address);

            Product product = new Product("test", 12.50, "dtest", LocalDate.now(), retailer, 3.5);

            Review review = new Review(2.5, "body", LocalDate.now(), customer, product);

            manager.persist(product);
            manager.persist(retailer);
            manager.persist(review);
            manager.getTransaction().commit();

            var ding = manager.find(Product.class, 1);
            ding.getRetailer();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
