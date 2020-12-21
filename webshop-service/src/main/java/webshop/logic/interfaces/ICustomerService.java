package webshop.logic.interfaces;

import webshop.service.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(int id)throws Exception;
    Customer saveCustomer(Customer customer)throws Exception;
    Customer updateCustomerById(int id, Customer customer)throws Exception;
    void removeCustomerById(int id)throws Exception;
}
