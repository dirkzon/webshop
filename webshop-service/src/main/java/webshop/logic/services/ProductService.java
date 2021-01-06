package webshop.logic.services;

import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Report;
import webshop.service.models.Review;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ProductService implements IProductService {

    private final IProductRepository repository;

    @Inject
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(int id) throws IllegalArgumentException {
        if (id < 0) throw new IllegalArgumentException("Id not valid");
        Product product = repository.getProductById(id);
        if (product.getReviews() != null) {
            for (Review review : product.getReviews()) {
                review.setProduct(null);
                review.getCustomer().setReviews(null);
                for (Report report : review.getReports()) {
                    report.setReview(null);
                    report.setRetailer(null);
                }
            }
        }
        return product;
    }

    @Override
    public boolean removeProductById(int id) throws IllegalArgumentException {
        if (id < 0) throw new IllegalArgumentException("Id not valid");
        Product productToRemove = repository.getProductById(id);
        if (productToRemove == null) return false;
        repository.removeProduct(productToRemove);
        return true;
    }

    @Override
    public Product updateProductById(int id, Product product) throws IllegalArgumentException {
        if (product.getDescription() == null ||
                product.getName() == null ||
                product.getImage() == null ||
                product.getPrice() == null ||
                product.getPrice() < 0 ||
                id == 0) {
            throw new IllegalArgumentException("Cannot update Product.");
        }
        return repository.updateProductById(id, product);
    }

    @Override
    public Review createReviewOnProductById(int id, Review review) throws IllegalArgumentException {
        if (review.getBody() == null ||
                review.getCustomer() == null ||
                review.getRating() == 0 ||
                id <= 0) {
            throw new IllegalArgumentException("Review not valid");
        }
        review.setCreated(LocalDate.now());
        repository.createReviewOnProductById(id, review);
        Product product = repository.getProductById(id);
        product.setRating(calculateRating(product.getReviews()));
        updateProductById(id, product);
        return review;
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields) throws IllegalArgumentException {
        if (!fields.isValid()) throw new IllegalArgumentException("Fields are not valid.");
        List<Product> products = repository.browseProducts(fields);
        for (Product product : products) product.setReviews(null);
        return products;
    }

    private double calculateRating(List<Review> reviews) {
        double output = 0.0;
        if (reviews != null) {
            for (Review review : reviews) {
                output += review.getRating();
            }
            output = output / reviews.size();
        }
        return output;
    }
}
