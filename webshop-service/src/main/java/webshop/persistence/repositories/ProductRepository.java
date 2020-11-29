package webshop.persistence.repositories;

import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.Customer;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductRepository implements IProductRepository {

    private EntityManager em;

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
        em.remove(product);
        em.getTransaction().commit();
    }

    @Override
    public Product updateProduct(Product product){
        em.getTransaction().begin();
        Product productToUpdate = getProductById(product.getId());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImage(product.getImage());
        em.getTransaction().commit();
        return product;
    }

    @Override
    public Review createReviewOnProduct(Review review){
        em.getTransaction().begin();
        review.setCustomer(em.getReference(Customer.class, review.getCustomer().getId()));
        review.setProduct(em.getReference(Product.class, review.getProduct().getId()));
        em.persist(review);
        em.getTransaction().commit();
        return review;
    }
}
