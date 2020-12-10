package webshop.logic.services;

import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;
import webshop.service.models.Review;

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
            return repository.updateCustomerById(id, customer);
        }
        return null;
    }

    public boolean removeCustomerById(int id){
        Customer customerToRemove = getCustomerById(id);
        if(customerToRemove == null) return false;
        repository.removeCustomer(customerToRemove);
        return true;
    }
}
