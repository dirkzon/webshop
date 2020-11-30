package webshop.logic.interfaces;

import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductService {
    Product getProductById(int id);
    boolean removeProductById(int id);
    Product updateProductById(int id, Product product);
    Review createReviewOnProductById(int id, Review review);
    List<Product> browseProducts(BrowseVars fields);
}
