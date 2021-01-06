package webshop.persistence.repositories;

import webshop.persistence.interfaces.ICustomerRepository;
import webshop.service.models.Customer;
import webshop.service.models.Report;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CustomerRepository implements ICustomerRepository {

    private final EntityManagerFactory emf;

    @Inject
    public CustomerRepository(EntityManagerFactory entityManagerFactory){
        emf = entityManagerFactory;
    }

    public Customer saveCustomer(Customer customer){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Customer.class, id);
        }finally{
            em.close();
        }
    }

    @Override
    public void removeCustomer(Customer customer){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            for (Review review : customer.getReviews()) {
                for(Report report : review.getReports()){
                    report.setRetailer(null);
                    report.getReview().setProduct(null);
                    report.getReview().setCustomer(null);
                    em.remove(em.contains(report) ? report : em.merge(report));
                }
                review.setProduct(null);
                em.remove(em.contains(review) ? review : em.merge(review));
            }
            em.remove(em.contains(customer) ? customer : em.merge(customer));
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

    @Override
    public Customer updateCustomerById(int id, Customer customer){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Customer customerToUpdate = em.find(Customer.class, id);
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setAvatar(customer.getAvatar());
            customerToUpdate.setAccount(customer.getAccount());
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
}
