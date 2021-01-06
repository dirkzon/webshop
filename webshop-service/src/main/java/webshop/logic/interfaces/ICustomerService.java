package webshop.logic.interfaces;

import javassist.NotFoundException;
import webshop.service.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(int id)throws NotFoundException;
    Customer saveCustomer(Customer customer);
    Customer updateCustomerById(int id, Customer customer);
    void removeCustomerById(int id);
}
