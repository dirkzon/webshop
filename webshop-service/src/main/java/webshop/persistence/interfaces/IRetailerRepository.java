package webshop.persistence.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerRepository {
    Retailer saveRetailer(Retailer retailer)throws Exception;
    Retailer getRetailerById(int id)throws Exception;
    void removeRetailer(Retailer retailer)throws Exception;
    Retailer updateRetailerById(int id, Retailer retailer)throws Exception;
    List<Product> getProductsInCatalog(int id)throws Exception;
    Product createNewProductInCatalog(int id, Product product)throws Exception;
}
