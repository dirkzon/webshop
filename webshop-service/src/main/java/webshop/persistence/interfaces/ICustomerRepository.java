package webshop.persistence.interfaces;

import webshop.service.models.Customer;

public interface ICustomerRepository {

    Customer saveCustomer(Customer customer);
    Customer findCustomerById(int id);
}
