package webshop.persistence.repositories;

import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;

public class CustomerRepository implements ICustomerRepository {

    private EntityManager em;

    @Inject
    public CustomerRepository(EntityManagerFactory entityManagerFactory){
        em = entityManagerFactory.createEntityManager();
    }

    public Customer saveCustomer(Customer customer){
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void removeCustomer(Customer customer){
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }

    @Override
    public Customer updateCustomerById(int id, Customer customer){
        em.getTransaction().begin();
        Customer customerToUpdate = getCustomerById(id);
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setAvatar(customer.getAvatar());
        customerToUpdate.setAccount(customer.getAccount());
        em.getTransaction().commit();
        return customer;
    }
}
