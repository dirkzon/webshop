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

    public Customer getCustomerById(int id)throws Exception{
        try{
            if(id < 0) throw new Exception("No id");
            Customer customer = repository.getCustomerById(id);
            for(Review review : customer.getReviews()){
                review.getProduct().setReviews(null);
                review.setCustomer(null);
            }
            return customer;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Customer saveCustomer(Customer customer)throws Exception{
        try{
            customer.setAvatar(new Image("https://cnaca.ca/wp-content/uploads/2018/10/user-icon-image-placeholder.jpg"));
            if(customer.getAccount() != null &&
                    customer.getAvatar() != null &&
                    customer.getAddress() != null){
                customer.getAccount().setJoined(LocalDate.now());
                return repository.saveCustomer(customer);
            }
            throw new Exception("customer not valid");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Customer updateCustomerById(int id, Customer customer)throws Exception{
        try{
            if(customer.getAccount() != null &&
                    customer.getAvatar() != null &&
                    customer.getAddress() != null &&
                    customer.getId() >= 0){
                customer.getAccount().setRole(UserRole.Customer);
                return repository.updateCustomerById(id, customer);
            }
            throw new Exception("Customer not valid.");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void removeCustomerById(int id)throws Exception{
        try{
            Customer customer = repository.getCustomerById(id);
            repository.removeCustomer(customer);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
