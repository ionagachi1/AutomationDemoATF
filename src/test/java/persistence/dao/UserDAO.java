package persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.HibernateFrameworkSetup;
import persistence.entity.User;

public class UserDAO {

    SessionFactory sessionFactory = HibernateFrameworkSetup.getSessionFactory();
    private final Logger logger = LoggerFactory.getLogger(UserDAO.class);


    public UserDAO() {
    }


    public User getUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User WHERE username = :username";
            return session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception ex) {
            logger.error("Error occurred while getting user by username: {}", ex.getMessage());
            logger.debug("Stack trace:", ex);
            return null;
        }
    }

    public void insertUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error("Error occurred while inserting user: {}", ex.getMessage());
        }
    }
}
