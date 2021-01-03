package webshop.persistence.repositories;

import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.Product;
import webshop.service.models.Retailer;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RetailerRepository implements IRetailerRepository {

    private final EntityManagerFactory emf;

    @Inject
    public RetailerRepository(EntityManagerFactory entityManagerFactory){
        emf = entityManagerFactory;
    }

    @Override
    public Retailer saveRetailer(Retailer retailer)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(retailer);
            em.getTransaction().commit();
            return retailer;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public Retailer getRetailerById(int id)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Retailer.class, id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public void removeRetailer(Retailer retailer)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            List<Product> products = getProductsInCatalog(retailer.getId());
            em.getTransaction().begin();
            for (Product product : products){
                for(Review review : product.getReviews()){
                    review.setCustomer(null);
                }
                em.remove(em.contains(product) ? product : em.merge(product));
            }
            em.remove(em.contains(retailer) ? retailer : em.merge(retailer));
            em.getTransaction().commit();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }

    }

    @Override
    public Retailer updateRetailerById(int id, Retailer retailer)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Retailer retailerToUpdate = em.find(Retailer.class, id);
            retailerToUpdate.setAvatar(retailer.getAvatar());
            retailerToUpdate.setAccount(retailer.getAccount());
            em.getTransaction().commit();
            return retailer;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public List<Product> getProductsInCatalog(int id)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "SELECT * FROM Products WHERE retailer_id = :id";
            Query query = em.createNativeQuery(sql, Product.class);
            query.setParameter("id", id);
            return query.getResultList();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public Product createNewProductInCatalog(int id, Product product)throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            product.setRetailer(em.getReference(Retailer.class, id));
            em.persist(product);
            em.getTransaction().commit();
            return product;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }
}
