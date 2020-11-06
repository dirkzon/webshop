package webshop.persistence.repositories;

import webshop.service.models.Category;
import webshop.service.models.Product;
import webshop.service.models.ProductReview;
import webshop.persistence.interfaces.IProductRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProductRepository implements IProductRepository {

    private EntityManager entityManager;

    @Inject
    public ProductRepository(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void DeleteProductById(String id) {
        entityManager.remove(GetProductById(id));
    }

    @Override
    public Product GetProductById(String id){
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product UpdateProductById(String id, Product product) {
        return entityManager.merge(product);
    }

    @Override
    public ProductReview CreateReviewById(String id, ProductReview review) {
        String sql = "INSERT INTO reviews(rating, body, created, customer_id, product_id, review_id) VALUES (?,?,?,?,?,?)";
        Query query = entityManager.createNativeQuery(sql);
        entityManager.getTransaction().begin();
        query.setParameter(1, review.getRating());
        query.setParameter(2, review.getBody());
        query.setParameter(3, LocalDate.now());
        query.setParameter(4, review.getCustomer().getId());
        query.setParameter(5, review.getProduct().getId());
        query.setParameter(6, "review:" + UUID.randomUUID());
        query.executeUpdate();
        entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public List<ProductReview> GetAllReviewsOnProductById(String id) {
        return null;
    }

    @Override
    public List<Product> BrowseProducts(int minPrice, int maxPrice, String query, String category, int targetRating) {
        String sql = "SELECT * FROM products WHERE name LIKE CONCAT('%',?1,'%') OR description LIKE CONCAT('%',?1,'%')";
        if(targetRating != 0){
            sql += " AND review BETWEEN 0 AND 3";
        }
        Query SearchQuery = entityManager.createNativeQuery(sql, Product.class);
        SearchQuery.setParameter(1, query);
        return SearchQuery.getResultList();
    }

    @Override
    public List<Category> GetAllCategories() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }

    @Override
    public Category GetCategoryById(String id) {
        return entityManager.find(Category.class, id);
    }
}
