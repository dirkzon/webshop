package webshop.persistence.repositories;

import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CustomerRepository implements ICustomerRepository {

    @Inject
    private EntityManager em;

    public CustomerRepository(EntityManagerFactory entityManagerFactory){
        em = entityManagerFactory.createEntityManager();
    }

    public Customer saveCustomer(Customer customer){
        em.persist(customer);
        return customer;
    }

    @Override
    public Customer findCustomerById(int id) {
        return em.find(Customer.class, id);
    }
}
