package webshop.logic.services;

import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.ws.rs.core.Context;
import java.time.LocalDate;

public class ProductService implements IProductService {

    @Context
    private IProductRepository repository;

    public ProductService(IProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product getProductById(int id){
        if(id < 0) return null;
        return repository.getProductById(id);
    }

    @Override
    public boolean removeProductById(int id){
        Product productToRemove = getProductById(id);
        if(productToRemove == null) return false;
        repository.removeProduct(productToRemove);
        return true;
    }

    @Override
    public Product updateProduct(Product product){
        if(product.getDescription() == null ||
                product.getName() == null ||
                product.getImage() == null ||
                product.getPrice() == null ||
                product.getPrice() < 0) {
            return null;
        }
        return repository.updateProduct(product);
    }

    @Override
    public Review createReviewOnProduct(Review review){
        if(review.getBody() == null ||
                review.getCustomer() == null ||
                review.getProduct() == null ||
                review.getRating() == 0){
            return null;
        }
        review.setCreated(LocalDate.now());
        return repository.createReviewOnProduct(review);
    }

}
