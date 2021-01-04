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

    private final EntityManagerFactory emf;

    @Inject
    public AccountRepository(EntityManagerFactory entityManagerFactory){
        emf = entityManagerFactory;
    }

    @Override
    public List<Account> getAccountByDetails(String details)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "SELECT * FROM accounts WHERE user_name = :details OR email = :details";
            Query query = em.createNativeQuery(sql, Account.class);
            query.setParameter("details", details);
            List result =  query.getResultList();
            if (result.isEmpty()) return null;
            return result;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }finally {
            em.close();
        }
    }

    //could be improved if i find a way to make a bidirectional mapping
    //between customer,retailer & account, But this also works.
    public int getUserIdFromAccountId(int id, UserRole role)throws Exception{
        EntityManager em = emf.createEntityManager();
        try{
            String sql;
            if(role == UserRole.CUSTOMER){
                sql = "SELECT id FROM customers WHERE account_id = :id";
            }else{
                sql = "SELECT id FROM retailers WHERE account_id = :id";
            }
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            var result =  query.getResultList();
            return (int) result.get(0);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }finally {
            em.close();
        }
    }
}
