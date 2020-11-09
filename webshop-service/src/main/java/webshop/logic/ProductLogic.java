package webshop.logic;

import webshop.persistence.interfaces.IProductRepository;
import webshop.persistence.repositories.ProductRepository;
import webshop.service.models.ProductReview;

import javax.inject.Inject;
import javax.persistence.Persistence;
import java.util.List;

public class ProductLogic {

    @Inject
    private IProductRepository repository = new ProductRepository(Persistence.createEntityManagerFactory("hibernate.memory"));

    public ProductReview CreateReview(String id, ProductReview review){
        var reviews = repository.GetAllReviewsOnProductById(id);
        reviews.add(review);
        Double newRating = CalculateNewRating(reviews);
        var product = repository.GetProductById(id);
        product.setRating(newRating);
        repository.UpdateProductById(id, product);
        //return repository.CreateReviewById(id, review);
        return null;
    }

    private Double CalculateNewRating(List<ProductReview> reviews){
        Double total = 0.0;
        for(ProductReview review : reviews){
            total += review.getRating();
        }
        return total / reviews.size();
    }
}
