package webshop.persistence.interfaces;

import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductRepository {
    Product getProductById(int id);
    void removeProduct(Product product);
    Product updateProductById(int id, Product product);
    Review createReviewOnProductById(int id, Review review);
    List<Product> browseProducts(BrowseVars fields);

}
