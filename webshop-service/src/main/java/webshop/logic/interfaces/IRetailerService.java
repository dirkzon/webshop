package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerService {
    Retailer getRetailerById(int id)throws IllegalArgumentException, NotFoundException;
    Retailer saveRetailer(Retailer retailer)throws IllegalArgumentException;
    Retailer updateRetailerById(int id, Retailer retailer)throws IllegalArgumentException;
    void removeRetailerById(int id);
    List<Product> getAllProductsInCatalog(int id)throws IllegalArgumentException;
    Product createNewProduct(int id, Product product)throws IllegalArgumentException;
}
