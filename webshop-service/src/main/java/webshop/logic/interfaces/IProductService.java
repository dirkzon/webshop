package webshop.logic.interfaces;

import webshop.service.models.Category;
import webshop.service.models.Product;
import webshop.service.models.ProductReview;

import java.util.List;

public interface IProductService {
    Product GetProductById(String id);
    ProductReview CreateReview(String id, ProductReview review);
    void DeleteProductById(String id);
    Product UpdateProductById(String id, Product product);
    List<ProductReview> GetAllReviewsOnProduct(String id);
    List<Product> BrowseProducts(int minPrice, int maxPrice, String query, String category, int targetRating);
    public List<Category> GetAllCategories();
    public Category GetCategoryById(String id);
}
