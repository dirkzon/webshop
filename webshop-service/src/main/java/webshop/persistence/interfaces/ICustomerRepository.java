package webshop.persistence.interfaces;

import webshop.service.models.ProductReview;

import java.util.List;

public interface ICustomerRepository extends IUserRepository{

    List<ProductReview> GetAllReviewsById(String id);
}
