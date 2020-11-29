package webshop.persistence.repositories;

import webshop.persistence.interfaces.IAccountRepository;
import webshop.service.models.Account;
import webshop.service.models.UserRole;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AccountRepository implements IAccountRepository {

    private EntityManager em;

    @Inject
    public AccountRepository(EntityManagerFactory entityManagerFactory){
        em = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Account> getAccountByDetails(String details){
        String sql = "SELECT * FROM accounts WHERE user_name = :details";
        Query query = em.createNativeQuery(sql, Account.class);
        query.setParameter("details", details);
        List<Account> result =  query.getResultList();
        if (result.isEmpty()) return null;
        return result;
    }

    public int getUserIdFromAccountId(int id, UserRole role){
        String sql;
        if(role == UserRole.Customer){
            sql = "SELECT id FROM customers WHERE account_id = :id";
        }else{
            sql = "SELECT id FROM retailers WHERE account_id = :id";
        }
        Query query = em.createNativeQuery(sql, int.class);
        query.setParameter("id", id);
        List<Integer> result =  query.getResultList();
        return result.get(0);
    }
}
