package webshop.logic.interfaces;

import webshop.service.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(int id)throws IllegalArgumentException;
    Customer saveCustomer(Customer customer)throws NullPointerException;
    Customer updateCustomerById(int id, Customer customer)throws NullPointerException;
    void removeCustomerById(int id);
}
