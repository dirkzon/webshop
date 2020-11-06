package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.persistence.interfaces.*;
import webshop.persistence.repositories.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    @Override
    protected void configure(){
        bind(ProductRepository.class).to(IProductRepository.class);
        bind(CustomerRepository.class).to(ICustomerRepository.class);
        bind(RetailerRepository.class).to(IRetailerRepository.class);

        bind(Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa")).to(EntityManagerFactory.class);
    }
}
