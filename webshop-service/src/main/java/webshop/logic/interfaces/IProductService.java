package webshop.logic.interfaces;

import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductService {
    Product getProductById(int id)throws Exception;
    boolean removeProductById(int id)throws Exception;
    Product updateProductById(int id, Product product)throws Exception;
    Review createReviewOnProductById(int id, Review review)throws Exception;
    List<Product> browseProducts(BrowseVars fields)throws Exception;
}
