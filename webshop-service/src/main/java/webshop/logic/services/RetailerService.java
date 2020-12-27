package webshop.logic.services;

import webshop.logic.interfaces.IRetailerService;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.*;

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
    public Retailer getRetailerById(int id)throws Exception{
        try{
            if(id < 0) throw new Exception("Invalid id");
            return repository.getRetailerById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Retailer saveRetailer(Retailer retailer)throws Exception{
        try{
            retailer.setAvatar(new Image("https://cnaca.ca/wp-content/uploads/2018/10/user-icon-image-placeholder.jpg"));
            if(retailer.getAccount() != null &&
                    retailer.getAvatar() != null){
                retailer.getAccount().setJoined(LocalDate.now());
                return repository.saveRetailer(retailer);
            }
            throw new Exception("customer not valid");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Retailer updateRetailerById(int id, Retailer retailer)throws Exception{
        try{
            if(retailer.getAccount() != null &&
                    retailer.getAvatar() != null &&
                    retailer.getId() >= 0){
                Retailer oldRetailer = getRetailerById(id);
                retailer.getAccount().setJoined(oldRetailer.getAccount().getJoined());
                retailer.getAccount().setRole(UserRole.Retailer);
                return repository.updateRetailerById(id, retailer);
            }
            throw new Exception("Retailer not valid.");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeRetailerById(int id)throws Exception{
        try{
            Retailer retailerToRemove = repository.getRetailerById(id);
            repository.removeRetailer(retailerToRemove);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProductsInCatalog(int id)throws Exception{
        try{
            if(id < 0) throw new Exception("Id not valid");
            List<Product> products = repository.getProductsInCatalog(id);
            for(Product product : products){
                for(Review review : product.getReviews()){
                    review.setProduct(null);
                    review.getCustomer().setReviews(null);
                }
            }
            return products;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Product createNewProduct(int id, Product product)throws Exception{
        try{
            if(product.getDescription() == null ||
                    product.getName() == null ||
                    product.getImage() == null ||
                    product.getPrice() == null ||
                    product.getPrice() < 0) {
                throw new Exception("Product not valid");
            }
            product.setCreated(LocalDate.now());
            Retailer retailer = repository.getRetailerById(id);
            product.setRetailer(retailer);
            product.setRating(0.0);
            Product newProduct = repository.createNewProductInCatalog(id, product);
            newProduct.setRetailer(retailer);
            return newProduct;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
