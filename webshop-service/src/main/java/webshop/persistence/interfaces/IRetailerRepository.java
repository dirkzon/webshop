package webshop.persistence.interfaces;

import webshop.service.models.Product;
import webshop.service.models.Retailer;

import java.util.List;

public interface IRetailerRepository extends IUserRepository {

    List<Product> GetAllProductsInCatalog(String id);
    Product CreateNewProductInCatalog(String retailerId, Product product);
}
