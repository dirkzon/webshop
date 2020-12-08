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

    private final EntityManager em;

    @Inject
    public ProductRepository(EntityManagerFactory entityManagerFactory){
        em = entityManagerFactory.createEntityManager();
    }

    @Override
    public Product getProductById(int id){
        return em.find(Product.class, id);
    }

    @Override
    public void removeProduct(Product product){
        em.getTransaction().begin();
        for(Review review : product.getReviews()){ review.setCustomer(null); }
        em.remove(product);
        em.getTransaction().commit();
    }

    //just a bit broken
    @Override
    public Product updateProductById(int id, Product product){
        Product productToUpdate = getProductById(id);
        em.getTransaction().begin();
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setName(product.getName());
        productToUpdate.setCreated(product.getCreated());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImage(product.getImage());
        productToUpdate.setRating(product.getRating());
        em.merge(productToUpdate);
        em.getTransaction().commit();
        return product;
    }

    @Override
    public Review createReviewOnProductById(int id, Review review){
        em.getTransaction().begin();
        review.setCustomer(em.getReference(Customer.class, review.getCustomer().getId()));
        review.setProduct(em.getReference(Product.class, id));
        em.persist(review);
        em.getTransaction().commit();
        return review;
    }

    @Override
    public List browseProducts(BrowseVars fields){
        String sql = "SELECT * FROM products Where name LIKE CONCAT('%',:search_query,'%') " +
                "And price > :min_price " +
                "And price < :max_price ";
        Query query = em.createNativeQuery(sql, Product.class);
        query.setParameter("search_query", fields.query);
        query.setParameter("min_price", fields.minPrice);
        query.setParameter("max_price", fields.maxPrice);
        return query.getResultList();
    }
}
