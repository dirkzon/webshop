package webshop.persistence.interfaces;

import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductRepository {
    Product getProductById(int id) throws Exception;
    void removeProduct(Product product)throws Exception;
    Product updateProductById(int id, Product product)throws Exception;
    Review createReviewOnProductById(int id, Review review)throws Exception;
    List<Product> browseProducts(BrowseVars fields)throws Exception;
}
