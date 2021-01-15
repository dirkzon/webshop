package webshop.service;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import webshop.logic.interfaces.*;
import webshop.logic.services.*;
import webshop.persistence.interfaces.*;
import webshop.persistence.repositories.*;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    private final String context;

    public DependencyBinder(String context){
        this.context = context;
    }

    @Override
    protected void configure(){

        //repos
        bind(CustomerRepository.class).to(ICustomerRepository.class);
        bind(RetailerRepository.class).to(IRetailerRepository.class);
        bind(AccountRepository.class).to(IAccountRepository.class);
        bind(ProductRepository.class).to(IProductRepository.class);
        bind(ReportRepository.class).to(IReportRepository.class);

        //services
        bind(CustomerService.class).to(ICustomerService.class);
        bind(RetailerService.class).to(IRetailerService.class);
        bind(AccountService.class).to(IAccountService.class);
        bind(ProductService.class).to(IProductService.class);
        bind(ReportService.class).to(IReportService.class);
        bind(KeyService.class).to(IKeyService.class);

        //context
        if(context.equals("Memory")){
            bind(Persistence.createEntityManagerFactory("hibernate.memory")).to(EntityManagerFactory.class);
        }else if(context.equals("SQL")){
            bind(Persistence.createEntityManagerFactory("hibernate.sql")).to(EntityManagerFactory.class);
        }
    }
}
