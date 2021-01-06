package webshop.persistence.repositories;

import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.BrowseVars;
import webshop.service.models.Customer;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private final EntityManagerFactory emf;

    @Inject
    public ProductRepository(EntityManagerFactory entityManagerFactory){
        emf = entityManagerFactory;
    }

    @Override
    public Product getProductById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Product.class, id);
        }finally {
            em.close();
        }
    }

    @Override
    public void removeProduct(Product product){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            for(Review review : product.getReviews()){
                review.setCustomer(null);
                em.remove(em.contains(review) ? review : em.merge(review));
            }
            product.setRetailer(null);
            em.remove(em.contains(product) ? product : em.merge(product));
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public Product updateProductById(int id, Product product){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Product productToUpdate = em.find(Product.class, id);
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setImage(product.getImage());
            productToUpdate.setRating(product.getRating());
            em.merge(productToUpdate);
            em.getTransaction().commit();
            return product;
        }finally {
            em.close();
        }
    }

    @Override
    public Review createReviewOnProductById(int id, Review review){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            review.setCustomer(em.getReference(Customer.class, review.getCustomer().getId()));
            review.setProduct(em.getReference(Product.class, id));
            em.persist(review);
            em.getTransaction().commit();
            return review;
        }finally {
            em.close();
        }
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields){
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "SELECT * FROM products Where name LIKE CONCAT('%',:search_query,'%') " +
                    "And price > :min_price " +
                    "And rating >= :min_rating ";
            if(fields.getMaxPrice() > 0) sql +="And price < :max_price ";
            Query query = em.createNativeQuery(sql, Product.class);
            query.setParameter("search_query", fields.getQuery());
            query.setParameter("min_price", fields.getMinPrice());
            query.setParameter("min_rating", fields.getMinRating());
            if(fields.getMaxPrice() > 0) query.setParameter("max_price", fields.getMaxPrice());
            return query.getResultList();
        }finally {
            em.close();
        }
    }
}
