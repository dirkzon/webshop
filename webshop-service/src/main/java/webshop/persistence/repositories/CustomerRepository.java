package webshop.persistence.repositories;

import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CustomerRepository implements ICustomerRepository {

    private EntityManager entityManager;

    @Inject
    public CustomerRepository(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public Customer UpdateCustomerById(String id, Customer customer) {
        return null;
    }

    @Override
    public Customer GetCustomerById(String id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer CreateNewCustomer(Customer customer) {
        return null;
    }

    @Override
    public void RemoveCustomerById(String id) {
        entityManager.remove(GetCustomerById(id));
    }
}
