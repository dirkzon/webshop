package webshop.logic.interfaces;

import webshop.service.models.AbstractUser;

public interface IUserService {
    AbstractUser GetUserById(String id);
    AbstractUser UpdateUserById(String id, AbstractUser updatedUser);
    void RemoveUserById(String id);
    AbstractUser CreateUser(AbstractUser newUser);
    AbstractUser IsUserValid(String userDetails);
}
