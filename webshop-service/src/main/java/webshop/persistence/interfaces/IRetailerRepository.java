package webshop.persistence.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerRepository {
    Retailer saveRetailer(Retailer retailer);
    Retailer getRetailerById(int id);
    void removeRetailer(Retailer retailer);
    Retailer updateRetailer(Retailer retailer);
    List<Product> getProductsInCatalog(int id);
    Product createNewProductInCatalog(int id, Product product);
}
