package webshop.persistence.interfaces;

import webshop.service.models.AbstractUser;

public interface IUserRepository {

    AbstractUser GetUserById(String id);
    AbstractUser UpdateUserById(String id, AbstractUser updatedUser);
    void RemoveUserById(String id);
    AbstractUser CreateUser(AbstractUser newUser);
    AbstractUser IsUserValid(String userName);
}
