package webshop.logic.services;

import javassist.NotFoundException;
import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.*;

import javax.inject.Inject;
import java.time.LocalDate;

import static webshop.logic.services.Constants.INVALID_ID;

public class CustomerService implements ICustomerService {

    private final ICustomerRepository repository;

    @Inject
    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomerById(int id)throws NotFoundException {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Customer customer = repository.getCustomerById(id);
        if(customer == null) throw new NotFoundException("Customer not found");
        if (customer.getReviews() != null) {
            for (Review review : customer.getReviews()) {
                review.getProduct().setReviews(null);
                review.setCustomer(null);
                for (Report report : review.getReports()) {
                    report.setReview(null);
                    report.setRetailer(null);
                }
            }
        }
        return customer;
    }

    public Customer saveCustomer(Customer customer) {
        customer.setAvatar(new Image("https://cnaca.ca/wp-content/uploads/2018/10/user-icon-image-placeholder.jpg"));
        if (customer.getAccount() != null &&
                customer.getAvatar() != null &&
                customer.getAddress() != null) {
            customer.getAccount().setJoined(LocalDate.now());
            return repository.saveCustomer(customer);
        }
        throw new NullPointerException("customer not valid");
    }

    public Customer updateCustomerById(int id, Customer customer){
        if (customer.getAccount() != null &&
                customer.getAvatar() != null &&
                customer.getAddress() != null &&
                customer.getId() >= 0) {
            customer.getAccount().setRole(UserRole.CUSTOMER);
            return repository.updateCustomerById(id, customer);
        }
        throw new NullPointerException("Customer not valid");
    }

    public void removeCustomerById(int id){
        Customer customer = repository.getCustomerById(id);
        repository.removeCustomer(customer);
    }
}
