package webshop.persistence.repositories;

import org.hibernate.Criteria;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.AbstractUser;
import webshop.service.models.Customer;
import webshop.service.models.ProductReview;
import webshop.service.models.Retailer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {

    private EntityManager entityManager;

    @Inject
    public CustomerRepository(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }


    @Override
    public List<ProductReview> GetAllReviewsById(String id) {
        String sql = "SELECT * FROM reviews WHERE customer_id = ?1";
        Query query = entityManager.createNativeQuery(sql, ProductReview.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public AbstractUser GetUserById(String id) {
        return entityManager.find(Customer.class, id);
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
        String sql = "SELECT * FROM customers WHERE name = :name OR email = :name";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter("name", userName);
        var result =  query.getResultList();
        if (result.isEmpty()){
            return null;
        }
        return (Customer) result.get(0);
    }
}
