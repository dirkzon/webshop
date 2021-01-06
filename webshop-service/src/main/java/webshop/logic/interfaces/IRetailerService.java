package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerService {
    Retailer getRetailerById(int id)throws NotFoundException;
    Retailer saveRetailer(Retailer retailer);
    Retailer updateRetailerById(int id, Retailer retailer);
    void removeRetailerById(int id);
    List<Product> getAllProductsInCatalog(int id);
    Product createNewProduct(int id, Product product);
}
