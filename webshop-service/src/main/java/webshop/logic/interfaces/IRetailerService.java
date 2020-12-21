package webshop.logic.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerService {
    Retailer getRetailerById(int id)throws Exception;
    Retailer saveRetailer(Retailer retailer)throws Exception;
    Retailer updateRetailerById(int id, Retailer retailer)throws Exception;
    void removeRetailerById(int id)throws Exception;
    List<Product> getAllProductsInCatalog(int id)throws Exception;
    Product createNewProduct(int id, Product product)throws Exception;
}
