package webshop.persistence.interfaces;

import webshop.service.models.Customer;

public interface ICustomerRepository {

    Customer UpdateCustomerById(String id, Customer customer);
    Customer GetCustomerById(String id);
    Customer CreateNewCustomer(Customer customer);
    void RemoveCustomerById(String id);
}
