package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.logic.interfaces.*;
import webshop.logic.services.*;
import webshop.persistence.interfaces.*;
import webshop.persistence.repositories.*;

import webshop.persistence.Context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    private Context context;

    public DependencyBinder(Context context){
        this.context = context;
    }

    @Override
    protected void configure(){

        //services
        bind(ProductService.class).to(IProductService.class);
        bind(CustomerService.class).to(ICustomerService.class);
        bind(RetailerService.class).to(IRetailerRepository.class);
        bind(AuthenticationService.class).to(IAuthenticationService.class);

        //repositories
        bind(ProductRepository.class).to(IProductRepository.class);
        bind(CustomerRepository.class).to(ICustomerRepository.class);
        bind(RetailerRepository.class).to(IRetailerRepository.class);
        bind(ReviewRepository.class).to(IReviewRepository.class);

        //context
        if(context == Context.Memory){
            bind(Persistence.createEntityManagerFactory("hibernate.memory")).to(EntityManagerFactory.class);
        }else if(context == Context.SQL){
            bind(Persistence.createEntityManagerFactory("hibernate.sql")).to(EntityManagerFactory.class);
        }
    }
}
