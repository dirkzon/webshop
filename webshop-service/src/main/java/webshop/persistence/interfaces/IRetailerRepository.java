package webshop.persistence.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerRepository {

    Retailer GetRetailerById(String id);
    Retailer CreateRetailer(Retailer retailer);
    void RemoveRetailerById(String id);
    Retailer UpdateRetailerById(String id, Retailer retailer);
    List<Product> GetAllProductsInCatalog(String id);
    Product CreateNewProductInCatalog(String retailerId, Product product);
}
