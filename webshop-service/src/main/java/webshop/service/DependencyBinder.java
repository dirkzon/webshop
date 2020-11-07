package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.persistence.Context;
import webshop.persistence.interfaces.*;
import webshop.persistence.repositories.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    Context context;

    public DependencyBinder(Context context){
        this.context = context;
    }

    @Override
    protected void configure(){
        bind(ProductRepository.class).to(IProductRepository.class);
        bind(CustomerRepository.class).to(ICustomerRepository.class);
        bind(RetailerRepository.class).to(IRetailerRepository.class);

        if(context == Context.Memory){
            bind(Persistence.createEntityManagerFactory("hibernate.memory")).to(EntityManagerFactory.class);
        }else if(context == Context.SQL){
            bind(Persistence.createEntityManagerFactory("hibernate.sql")).to(EntityManagerFactory.class);
        }
    }
}
