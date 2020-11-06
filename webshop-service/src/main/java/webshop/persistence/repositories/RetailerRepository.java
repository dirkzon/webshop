package webshop.persistence.repositories;

import webshop.service.models.Product;
import webshop.service.models.Retailer;
import webshop.persistence.interfaces.IRetailerRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RetailerRepository implements IRetailerRepository {

    private EntityManager entityManager;

    @Inject
    public RetailerRepository(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public Retailer GetRetailerById(String id) {
        return entityManager.find(Retailer.class, id);
    }

    @Override
    public Retailer CreateRetailer(Retailer retailer) {
        return null;
    }

    @Override
    public void RemoveRetailerById(String id) {

    }

    @Override
    public Retailer UpdateRetailerById(String id, Retailer retailer) {
        return null;
    }

    @Override
    public List<Product> GetAllProductsInCatalog(String id) {
        String sql = "SELECT * FROM Products WHERE retailer_id = :id";
        Query query = entityManager.createNativeQuery(sql, Product.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Product CreateNewProductInCatalog(String retailerId, Product product) {
        return null;
    }
}
