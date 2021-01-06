package webshop.logic.interfaces;

import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import java.util.List;

public interface IProductService {
    Product getProductById(int id)throws IllegalArgumentException;
    boolean removeProductById(int id)throws IllegalArgumentException;
    Product updateProductById(int id, Product product)throws IllegalArgumentException;
    Review createReviewOnProductById(int id, Review review)throws IllegalArgumentException;
    List<Product> browseProducts(BrowseVars fields)throws IllegalArgumentException;
}
