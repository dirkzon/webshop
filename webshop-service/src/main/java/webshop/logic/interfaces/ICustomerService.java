package webshop.logic.interfaces;

import webshop.service.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(int id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomerById(Customer customer);
    boolean removeCustomerById(int id);
}
