package webshop.persistence.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Review;

public interface IProductRepository {
    Product getProductById(int id);
    void removeProduct(Product product);
    Product updateProduct(Product product);
    Review createReviewOnProduct(Review review);
}
