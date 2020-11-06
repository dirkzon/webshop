package webshop.persistence.repositories;

import webshop.persistence.interfaces.IReviewRepository;
import webshop.service.models.ProductReview;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ReviewRepository implements IReviewRepository {

    private EntityManager entityManager;

    @Inject
    public ReviewRepository(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void DeleteReviewById(String id) {
        var review = entityManager.find(ProductReview.class, id);
        entityManager.remove(review);
    }
}
