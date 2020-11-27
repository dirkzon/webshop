package webshop.logic.services;

import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.AbstractUser;
import webshop.service.models.ProductReview;

import javax.inject.Inject;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository repository;

    @Inject
    public CustomerService(ICustomerRepository repository){
        this.repository = repository;
    }

    @Override
    public List<ProductReview> GetAllReviewsById(String id) {
        return repository.GetAllReviewsById(id);
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
