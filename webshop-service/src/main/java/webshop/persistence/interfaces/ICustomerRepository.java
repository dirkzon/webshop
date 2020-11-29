package webshop.persistence.interfaces;

import webshop.service.models.Customer;

public interface ICustomerRepository {

    Customer saveCustomer(Customer customer);
    Customer getCustomerById(int id);
    void removeCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
}
