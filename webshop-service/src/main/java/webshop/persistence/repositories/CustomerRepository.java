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

    public Customer saveCustomer(Customer customer)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerById(int id)throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Customer.class, id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public void removeCustomer(Customer customer)throws Exception{
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
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public Customer updateCustomerById(int id, Customer customer)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Customer customerToUpdate = em.find(Customer.class, id);
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setAvatar(customer.getAvatar());
            customerToUpdate.setAccount(customer.getAccount());
            em.getTransaction().commit();
            return customer;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            em.close();
        }
    }
}
