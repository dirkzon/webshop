package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.persistence.interfaces.ICustomerRepository;
import webshop.persistence.repositories.CustomerRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    private String context;

    public DependencyBinder(String context){
        this.context = context;
    }

    @Override
    protected void configure(){

        //repos
        bind(CustomerRepository.class).to(ICustomerRepository.class);

        //context
        if(context == "Memory"){
            bind(Persistence.createEntityManagerFactory("hibernate.memory")).to(EntityManagerFactory.class);
        }else if(context == "SQL"){
            bind(Persistence.createEntityManagerFactory("hibernate.sql")).to(EntityManagerFactory.class);
        }
    }
}
