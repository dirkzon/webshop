package webshop.logic.services;

import webshop.logic.interfaces.ICustomerService;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;

import javax.ws.rs.core.Context;
import java.time.LocalDate;

public class CustomerService implements ICustomerService {

    @Context
    private ICustomerRepository repository;

    public CustomerService(ICustomerRepository repository){
        this.repository = repository;
    }

    public Customer getCustomerById(int id){
        if(id < 0) return null;
        return repository.getCustomerById(id);
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

    public Customer updateCustomerById(Customer customer){
        if(customer.getAccount() != null &&
                customer.getAvatar() != null &&
                customer.getAddress() != null &&
                customer.getId() >= 0){
            return repository.updateCustomer(customer);
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
