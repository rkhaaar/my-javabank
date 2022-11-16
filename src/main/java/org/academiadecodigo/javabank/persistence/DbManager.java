package org.academiadecodigo.javabank.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {


    private String dbUrl;


private EntityManagerFactory emf;


    public DbManager(String dbUrl) {
        this.dbUrl=dbUrl;
        this.emf= Persistence.createEntityManagerFactory(dbUrl);
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    /* public Connection getConnection() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }*/
}
