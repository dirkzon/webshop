package webshop.logic.services;

import javassist.NotFoundException;
import webshop.logic.interfaces.IRetailerService;
import webshop.persistence.interfaces.IRetailerRepository;
import webshop.service.models.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static webshop.logic.services.Constants.INVALID_ID;

public class RetailerService implements IRetailerService {

    private final IRetailerRepository repository;

    @Inject
    public RetailerService(IRetailerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Retailer getRetailerById(int id) throws NotFoundException {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Retailer retailer = repository.getRetailerById(id);
        if (retailer == null) throw new NotFoundException("Retailer not found");
        return retailer;
    }

    @Override
    public Retailer saveRetailer(Retailer retailer) {
        retailer.setAvatar(new Image("https://cnaca.ca/wp-content/uploads/2018/10/user-icon-image-placeholder.jpg"));
        if (retailer.getAccount() != null &&
                retailer.getAvatar() != null) {
            retailer.getAccount().setJoined(LocalDate.now());
            return repository.saveRetailer(retailer);
        }
        throw new IllegalArgumentException("customer not valid");
    }

    @Override
    public Retailer updateRetailerById(int id, Retailer retailer) {
        if (retailer.getAccount() != null &&
                retailer.getAvatar() != null &&
                retailer.getId() >= 0) {
            Retailer oldRetailer = repository.getRetailerById(id);
            retailer.getAccount().setJoined(oldRetailer.getAccount().getJoined());
            retailer.getAccount().setRole(UserRole.RETAILER);
            return repository.updateRetailerById(id, retailer);
        }
        throw new IllegalArgumentException("Retailer not valid.");
    }

    @Override
    public void removeRetailerById(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Retailer retailerToRemove = repository.getRetailerById(id);
        repository.removeRetailer(retailerToRemove);
    }

    @Override
    public List<Product> getAllProductsInCatalog(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        List<Product> products = repository.getProductsInCatalog(id);
        for (Product product : products) {
            for (Review review : product.getReviews()) {
                review.setProduct(null);
                review.getCustomer().setReviews(null);
                review.setReports(null);
            }
        }
        return products;
    }

    @Override
    public Product createNewProduct(int id, Product product) {
        if (product.getDescription() == null ||
                product.getName() == null ||
                product.getImage() == null ||
                product.getPrice() == null ||
                product.getPrice() < 0) {
            throw new IllegalArgumentException("Product not valid");
        }
        product.setCreated(LocalDate.now());
        Retailer retailer = repository.getRetailerById(id);
        product.setRetailer(retailer);
        product.setRating(0.0);
        Product newProduct = repository.createNewProductInCatalog(id, product);
        newProduct.setRetailer(retailer);
        return newProduct;
    }
}
