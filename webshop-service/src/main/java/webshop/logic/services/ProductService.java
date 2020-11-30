package webshop.logic.services;

import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import java.time.LocalDate;
import java.util.List;

public class ProductService implements IProductService {

    @Context
    private IProductRepository repository;

    @Inject
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
    public Product updateProductById(int id, Product product){
        if(product.getDescription() == null ||
                product.getName() == null ||
                product.getImage() == null ||
                product.getPrice() == null ||
                product.getPrice() < 0 ||
                id == 0) {
            return null;
        }
        return repository.updateProductById(id, product);
    }

    @Override
    public Review createReviewOnProductById(int id, Review review){
        if(review.getBody() == null ||
                review.getCustomer() == null ||
                review.getProduct() == null ||
                review.getRating() == 0 ||
                id == 0){
            return null;
        }
        review.setCreated(LocalDate.now());
        return repository.createReviewOnProductById(id, review);
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields){
        if(!fields.isValid()) return null;
        return repository.browseProducts(fields);
    }
}
