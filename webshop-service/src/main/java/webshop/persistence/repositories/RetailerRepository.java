package webshop.persistence.repositories;

import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.Product;
import webshop.service.models.Retailer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RetailerRepository implements IRetailerRepository {

    private EntityManager em;

    @Inject
    public RetailerRepository(EntityManagerFactory entityManagerFactory){
        em = entityManagerFactory.createEntityManager();
    }

    @Override
    public Retailer saveRetailer(Retailer retailer){
        em.getTransaction().begin();
        em.persist(retailer);
        em.getTransaction().commit();
        return retailer;
    }

    @Override
    public Retailer getRetailerById(int id){
        return em.find(Retailer.class, id);
    }

    @Override
    public void removeRetailer(Retailer retailer){
        em.getTransaction().begin();
        em.remove(retailer);
        em.getTransaction().commit();
    }

    @Override
    public Retailer updateRetailerById(int id, Retailer retailer){
        em.getTransaction().begin();
        Retailer customerToUpdate = getRetailerById(id);
        customerToUpdate.setAvatar(retailer.getAvatar());
        customerToUpdate.setAccount(retailer.getAccount());
        em.getTransaction().commit();
        return retailer;
    }

    @Override
    public List<Product> getProductsInCatalog(int id){
        String sql = "SELECT * FROM Products WHERE retailer_id = :id";
        Query query = em.createNativeQuery(sql, Product.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Product createNewProductInCatalog(int id, Product product) {
        em.getTransaction().begin();
        product.setRetailer(em.getReference(Retailer.class, id));
        em.persist(product);
        em.getTransaction().commit();
        return product;
    }
}
