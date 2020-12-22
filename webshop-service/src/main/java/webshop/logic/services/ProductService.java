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
    public Product getProductById(int id)throws Exception{
        if(id < 0) return null;
        try{
            Product product = repository.getProductById(id);
            for(Review review : product.getReviews()){
                review.setProduct(null);
                review.getCustomer().setReviews(null);
            }
            return product;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean removeProductById(int id)throws Exception{
        try{
            Product productToRemove = repository.getProductById(id);
            if(productToRemove == null) return false;
            repository.removeProduct(productToRemove);
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Product updateProductById(int id, Product product)throws Exception{
        try{
            if(product.getDescription() == null ||
                    product.getName() == null ||
                    product.getImage() == null ||
                    product.getPrice() == null ||
                    product.getPrice() < 0 ||
                    id == 0) {
                throw new Exception("Cannot update Product");
            }
            return repository.updateProductById(id, product);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Review createReviewOnProductById(int id, Review review)throws Exception{
        try{
            if(review.getBody() == null ||
                    review.getCustomer() == null ||
                    review.getRating() == 0 ||
                    id <= 0){
                return null;
            }
            review.setCreated(LocalDate.now());
            repository.createReviewOnProductById(id, review);
            Product product = repository.getProductById(id);
            product.setRating(calculateRating(product.getReviews()));
            updateProductById(id, product);
            return review;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields)throws Exception{
        try{
            List<Product> products= repository.browseProducts(fields);
            for(Product product : products) product.setReviews(null);
            return products;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private double calculateRating(List<Review> reviews){
        double output = 0.0;
        for(Review review : reviews){
            output += review.getRating();
        }
        return output / reviews.size();
    }
}
