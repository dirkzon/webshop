package webshop.logic.interfaces;

import webshop.service.models.Product;

import java.util.List;

public interface IRetailerService extends IUserService{
    List<Product> GetAllProductsInCatalog(String id);
    Product CreateNewProductInCatalog(String retailerId, Product product);
}
