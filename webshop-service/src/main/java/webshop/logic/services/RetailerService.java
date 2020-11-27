package webshop.logic.services;

import webshop.logic.interfaces.IRetailerService;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.AbstractUser;
import webshop.service.models.Product;

import javax.inject.Inject;
import java.util.List;

public class RetailerService implements IRetailerService {

    private IRetailerRepository repository;

    @Inject
    public RetailerService(IRetailerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> GetAllProductsInCatalog(String id) {
        return repository.GetAllProductsInCatalog(id);
    }

    @Override
    public Product CreateNewProductInCatalog(String retailerId, Product product) {
        return repository.CreateNewProductInCatalog(retailerId, product);
    }

    @Override
    public AbstractUser GetUserById(String id) {
        return repository.GetUserById(id);
    }

    @Override
    public AbstractUser UpdateUserById(String id, AbstractUser updatedUser) {
        return repository.UpdateUserById(id, updatedUser);
    }

    @Override
    public void RemoveUserById(String id) {
        repository.RemoveUserById(id);
    }

    @Override
    public AbstractUser CreateUser(AbstractUser newUser) {
        return repository.CreateUser(newUser);
    }

    @Override
    public AbstractUser IsUserValid(String userDetails) {
        return repository.IsUserValid(userDetails);
    }
}
