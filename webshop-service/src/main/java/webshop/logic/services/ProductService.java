package webshop.logic.services;

import javassist.NotFoundException;
import webshop.logic.interfaces.IProductService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Report;
import webshop.service.models.Review;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static webshop.logic.services.Constants.INVALID_ID;

public class ProductService implements IProductService {

    private final IProductRepository repository;

    @Inject
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(int id)throws NotFoundException {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Product product = repository.getProductById(id);
        if(product == null) throw new NotFoundException("Product not found");
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
    public boolean removeProductById(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Product productToRemove = repository.getProductById(id);
        if (productToRemove == null) return false;
        repository.removeProduct(productToRemove);
        return true;
    }

    @Override
    public Product updateProductById(int id, Product product) {
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
    public Review createReviewOnProductById(int id, Review review) {
        if (review.getBody() == null ||
                review.getCustomer() == null ||
                review.getRating() == 0 ||
                id <= 0) {
            throw new IllegalArgumentException("Review not valid");
        }
        review.setCreated(LocalDate.now());
        repository.createReviewOnProductById(id, review);
        Product product = repository.getProductById(id);
        product.calculateRating();
        updateProductById(id, product);
        return review;
    }

    @Override
    public List<Product> browseProducts(BrowseVars fields) {
        if (!fields.isValid()) throw new IllegalArgumentException("Fields are not valid.");
        List<Product> products = repository.browseProducts(fields);
        for (Product product : products) product.setReviews(null);
        return products;
    }
}
