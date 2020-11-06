package webshop.persistence.interfaces;

import webshop.service.models.Category;
import webshop.service.models.Product;
import webshop.service.models.ProductReview;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface IProductRepository {

    void DeleteProductById(String id);
    Product GetProductById(String id);
    Product UpdateProductById(String id, Product product);
    ProductReview CreateReviewById(String id, ProductReview review);
    List<ProductReview> GetAllReviewsOnProductById(String id);
    List<Product> BrowseProducts(int minPrice, int maxPrice, String query, String category, int targetRating);
    List<Category> GetAllCategories();
    Category GetCategoryById(String id);
}
