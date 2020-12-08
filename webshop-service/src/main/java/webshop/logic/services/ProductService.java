package webshop.logic.services;

import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ProductService implements IProductService {

    private final IProductRepository repository;

    @Inject
    public ProductService(IProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product getProductById(int id){
        if(id < 0) return null;
        Product product = repository.getProductById(id);
        for(Review review : product.getReviews()){
            review.setProduct(null);
            review.getCustomer().setReviews(null);
        }
        return product;
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
                id == 0 ||
                product.getRetailer() == null) {
            return null;
        }
        return repository.updateProductById(id, product);
    }

    @Override
    public Review createReviewOnProductById(int id, Review review){
        if(review.getBody() == null ||
                review.getCustomer() == null ||
                review.getRating() == 0 ||
                id <= 0){
            return null;
        }
        review.setCreated(LocalDate.now());
        repository.createReviewOnProductById(id, review);
        Product product = getProductById(id);
        double newRating = calculateRating(product.getReviews());
        product.setRating(newRating);
        updateProductById(id, product);
        return review;
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields){
        if(!fields.isValid()) return null;
        List<Product> products = repository.browseProducts(fields);
        for(Product product : products){
            product.setReviews(null);
        }
        return products;
    }

    private double calculateRating(List<Review> reviews){
        double output = 0.0;
        for(Review review : reviews){
            output += review.getRating();
        }
        return output / reviews.size();
    }
}
