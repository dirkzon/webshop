package webshop.logic.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Review;

public interface IProductService {
    Product getProductById(int id);
    boolean removeProductById(int id);
    Product updateProduct(Product product);
    Review createReviewOnProduct(Review review);
}
