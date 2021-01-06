package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductService {
    Product getProductById(int id)throws NotFoundException;
    boolean removeProductById(int id);
    Product updateProductById(int id, Product product);
    Review createReviewOnProductById(int id, Review review);
    List<Product> browseProducts(BrowseVars fields);
}
