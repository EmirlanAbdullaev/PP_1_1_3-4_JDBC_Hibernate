package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final Session session = Util.getSessionFactory().openSession();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
            return session.createQuery("from User", User.class).list();

    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = session.beginTransaction();
        String sql = "DELETE TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }
}
