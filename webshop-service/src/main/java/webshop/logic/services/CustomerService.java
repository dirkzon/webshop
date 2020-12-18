package webshop.logic.services;

import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;
import webshop.service.models.Image;
import webshop.service.models.Review;
import webshop.service.models.UserRole;

import javax.inject.Inject;
import java.time.LocalDate;

public class CustomerService implements ICustomerService {

    private final ICustomerRepository repository;

    @Inject
    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomerById(int id){
        if(id < 0) return null;
        Customer customer = repository.getCustomerById(id);
        for(Review review : customer.getReviews()){
            review.getProduct().setReviews(null);
            review.setCustomer(null);
        }
        return customer;
    }

    public Customer saveCustomer(Customer customer){
        customer.setAvatar(new Image("https://cnaca.ca/wp-content/uploads/2018/10/user-icon-image-placeholder.jpg"));
        if(customer.getAccount() != null &&
                customer.getAvatar() != null &&
                customer.getAddress() != null){
            customer.getAccount().setJoined(LocalDate.now());
            return repository.saveCustomer(customer);
        }
        return null;
    }

    public Customer updateCustomerById(int id, Customer customer){
        if(customer.getAccount() != null &&
                customer.getAvatar() != null &&
                customer.getAddress() != null &&
                customer.getId() >= 0){
            Customer oldCustomer = repository.getCustomerById(id);
            customer.getAccount().setJoined(oldCustomer.getAccount().getJoined());
            customer.getAccount().setRole(UserRole.Customer);
            return repository.updateCustomerById(id, customer);
        }
        return null;
    }

    public boolean removeCustomerById(int id){
        Customer customerToRemove = repository.getCustomerById(id);
        if(customerToRemove == null) return false;
        repository.removeCustomer(customerToRemove);
        return true;
    }
}
