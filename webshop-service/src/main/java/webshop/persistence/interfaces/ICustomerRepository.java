package webshop.persistence.interfaces;

import webshop.service.models.Customer;

public interface ICustomerRepository {

    Customer saveCustomer(Customer customer)throws Exception;
    Customer getCustomerById(int id)throws Exception;
    void removeCustomer(Customer customer)throws Exception;
    Customer updateCustomerById(int id, Customer customer)throws Exception;
}
