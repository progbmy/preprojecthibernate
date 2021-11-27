package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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
                        "LASTNAME VARCHAR(40), "+
                        "AGE TINYINT)").executeUpdate();
        transaction.commit();
        System.out.println("Создана таблица");
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Таблица удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user.setId(id);
            session.delete(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User ").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
       try (Session session = Util.getSessionFactory().openSession()){
           transaction = session.beginTransaction();
           session.createQuery("delete from User ").executeUpdate();
           transaction.commit();
       } catch (Exception e) {
           e.printStackTrace();
           assert transaction != null;
           transaction.rollback();
       }
    }
}
