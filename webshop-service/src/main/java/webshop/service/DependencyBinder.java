package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.logic.interfaces.*;
import webshop.logic.services.*;
import webshop.persistence.interfaces.*;
import webshop.persistence.repositories.*;

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
        bind(RetailerRepository.class).to(IRetailerRepository.class);
        bind(AccountRepository.class).to(IAccountRepository.class);

        //services
        bind(CustomerService.class).to(ICustomerService.class);
        bind(RetailerService.class).to(IRetailerService.class);
        bind(AccountService.class).to(IAccountService.class);

        //context
        if(context == "Memory"){
            bind(Persistence.createEntityManagerFactory("hibernate.memory")).to(EntityManagerFactory.class);
        }else if(context == "SQL"){
            bind(Persistence.createEntityManagerFactory("hibernate.sql")).to(EntityManagerFactory.class);
        }
    }
}
