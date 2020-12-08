package webshop.logic.services;

import webshop.logic.interfaces.IRetailerService;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.Product;
import webshop.service.models.Retailer;
import webshop.service.models.Review;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class RetailerService implements IRetailerService {

    private final IRetailerRepository repository;

    @Inject
    public RetailerService(IRetailerRepository repository){
        this.repository = repository;
    }

    @Override
    public Retailer getRetailerById(int id){
        if(id < 0) return null;
        return repository.getRetailerById(id);
    }

    @Override
    public Retailer saveRetailer(Retailer retailer){
        if(retailer.getAccount() != null &&
                retailer.getAvatar() != null){
            retailer.getAccount().setJoined(LocalDate.now());
            return repository.saveRetailer(retailer);
        }
        return null;
    }

    @Override
    public Retailer updateRetailerById(int id, Retailer retailer){
        if(retailer.getAccount() != null &&
                retailer.getAvatar() != null &&
                retailer.getId() >= 0){
            return repository.updateRetailerById(id, retailer);
        }
        return null;
    }

    @Override
    public boolean removeRetailerById(int id){
        Retailer retailerToRemove = getRetailerById(id);
        if(retailerToRemove == null) return false;
        repository.removeRetailer(retailerToRemove);
        return true;
    }

    @Override
    public List<Product> getAllProductsInCatalog(int id){
        if(id < 0) return null;
        List<Product> products = repository.getProductsInCatalog(id);
        for(Product product : products){
            for(Review review : product.getReviews()){
                review.setProduct(null);
                review.getCustomer().setReviews(null);
            }
        }
        return products;
    }

    @Override
    public Product createNewProduct(int id, Product product){
        if(product.getDescription() == null ||
                product.getName() == null ||
                product.getImage() == null ||
                product.getPrice() == null ||
                product.getPrice() < 0) {
            return null;
        }
        product.setCreated(LocalDate.now());
        return repository.createNewProductInCatalog(id, product);
    }
}
