package webshop.service.logic;

import webshop.service.models.Product;
import webshop.service.models.ProductReview;
import webshop.persistence.interfaces.IProductRepository;

import javax.inject.Inject;

public class ProductService {

    @Inject
    IProductRepository repository;

    public Product GetProductById(String id){
        return repository.GetProductById(id);
    }

    public void DeleteProductById(String id){
        repository.DeleteProductById(id);
    }

    public Product UpdateProductById(String id, Product product){
        var updatedProduct = repository.UpdateProductById(id, product);
        return updatedProduct;
    }

    public void CreateReviewOnProductById(String id, ProductReview review){
        repository.CreateReviewById(id, review);
    }
}
