package webshop.logic.interfaces;

import webshop.service.models.AbstractUser;
import webshop.service.models.ProductReview;

import java.util.List;

public interface ICustomerService extends IUserService{
    List<ProductReview> GetAllReviewsById(String id);
    AbstractUser IsUserValid(String userName);
}
