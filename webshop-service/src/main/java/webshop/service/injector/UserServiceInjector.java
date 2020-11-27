package webshop.service.injector;

import org.glassfish.hk2.api.IterableProvider;
import webshop.logic.interfaces.IUserService;

import javax.inject.Inject;

public class UserServiceInjector {

    @Inject
    private IterableProvider<IUserService> interfaceProvider;

    public IUserService getImplementation(final String type_){
        return interfaceProvider.named(type_).get();
    }
}
