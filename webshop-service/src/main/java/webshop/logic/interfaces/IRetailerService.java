package webshop.logic.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerService {
    Retailer getRetailerById(int id);
    Retailer saveRetailer(Retailer retailer);
    Retailer updateRetailer(Retailer retailer);
    boolean removeRetailerById(int id);
    List<Product> getAllProductsInCatalog(int id);
    Product createNewProduct(int id, Product product);
}
