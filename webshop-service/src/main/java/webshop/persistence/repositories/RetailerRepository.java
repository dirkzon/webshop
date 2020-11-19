package webshop.persistence.repositories;

import webshop.service.models.AbstractUser;
import webshop.service.models.Customer;
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

    @Override
    public AbstractUser GetUserById(String id) {
        return entityManager.find(Retailer.class, id);
    }

    @Override
    public AbstractUser UpdateUserById(String id, AbstractUser updatedUser) {
        return null;
    }

    @Override
    public void RemoveUserById(String id) {

    }

    @Override
    public AbstractUser CreateUser(AbstractUser newUser) {
        return null;
    }

    @Override
    public AbstractUser IsUserValid(String userName) {
        String sql = "SELECT * FROM retailers WHERE name = :name OR email = :name";
        Query query = entityManager.createNativeQuery(sql, Retailer.class);
        query.setParameter("name", userName);
        var result =  query.getResultList();
        if (result.size() == 0){
            return null;
        }
        return (Retailer) result.get(0);
    }
}
