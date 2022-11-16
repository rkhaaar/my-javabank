package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.persistence.DbManager;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.sql.*;

public class JdbcAccountService implements AccountService {

    private DbManager dbManager;
    private AccountFactory accountFactory;

    public JdbcAccountService(DbManager dbManager, AccountFactory accountFactory) {
        this.dbManager = dbManager;
        this.accountFactory = accountFactory;
    }

    @Override
    public Account findById(Integer id) {

        EntityManager em = dbManager.getEmf().createEntityManager();
        try {
            return em.find(Account.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void add(Account account) {

        EntityManager em = dbManager.getEmf().createEntityManager();
        try {

            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            //return addedAccount;
        } catch (RollbackException caca) {
            em.getTransaction().rollback();//add return null check if we can change the method returning void and if makes sense

        } finally {
            if (em != null) {
                em.close();
            }
        }
           /* String query = "INSERT INTO account(account_type, balance, customer_id) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement statement = dbManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getAccountType().name());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getCustomerId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if(generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void deposit(int id, double amount) {
        EntityManager em = dbManager.getEmf().createEntityManager();
        Account account = findById(id);

        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }
        try {
            em.getTransaction().begin();
            account.credit(amount);
            em.getTransaction().commit();
        } catch (RollbackException merda) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        EntityManager em = dbManager.getEmf().createEntityManager();
        Account account = findById(id);
        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }
        try {
            em.getTransaction().begin();
            account.debit(amount);
            em.getTransaction().commit();
        } catch (RollbackException merdagrossa) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = findById(srcId);
        Account dstAccount = findById(dstId);
        EntityManager em = dbManager.getEmf().createEntityManager();

        if (srcAccount == null || dstAccount == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        try {
            if (srcAccount.canDebit(amount)) {
                em.getTransaction().begin();
                srcAccount.debit(amount);
                dstAccount.credit(amount);
                em.getTransaction().commit();
            }
        } catch (RollbackException shit) {
            em.getTransaction().rollback();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
