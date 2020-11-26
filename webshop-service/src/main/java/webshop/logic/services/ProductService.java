package webshop.logic.services;

import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.persistence.repositories.ProductRepository;
import webshop.service.models.Category;
import webshop.service.models.Product;
import webshop.service.models.ProductReview;

import javax.inject.Inject;
import javax.persistence.Persistence;
import java.util.List;

public class ProductService implements IProductService {

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

    public Product GetProductById(String id){
        return repository.GetProductById(id);
    }

    public void DeleteProductById(String id){
        repository.DeleteProductById(id);
    }

    public Product UpdateProductById(String id, Product product){
        var updatedProduct = repository.UpdateProductById(id, product);
        return updatedProduct;
    }

    @Override
    public List<ProductReview> GetAllReviewsOnProduct(String id) {
        return repository.GetAllReviewsOnProductById(id);
    }

    @Override
    public List<Product> BrowseProducts(int minPrice, int maxPrice, String query, String category, int targetRating) {
        return repository.BrowseProducts(minPrice, maxPrice, query, category, targetRating);
    }

    @Override
    public List<Category> GetAllCategories() {
        return repository.GetAllCategories();
    }

    @Override
    public Category GetCategoryById(String id) {
        return repository.GetCategoryById(id);
    }
}
