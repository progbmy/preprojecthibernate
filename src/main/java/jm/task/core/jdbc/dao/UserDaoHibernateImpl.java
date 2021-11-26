package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(ID BIGINT PRIMARY KEY AUTO_INCREMENT, "+
                        "NAME VARCHAR(25), "+
                        "LAST_NAME VARCHAR(40), "+
                        "AGE TINYINT)").executeUpdate();
        transaction.commit();
        System.out.println("Создана таблица");

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users");
        transaction.commit();
        System.out.println("База удалена");


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User");
            transaction.commit();
        } catch (Exception e) {
            if (transaction==null) {
                transaction.rollback();
            }
        } finally {
            if (session==null) {
                session.close();
            }
        }

    }
}
