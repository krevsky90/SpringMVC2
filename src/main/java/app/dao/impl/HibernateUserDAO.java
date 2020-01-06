package app.dao.impl;

import app.dao.UserDAO;
import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAllUsers() {
        /**
         * write queries using HQL (Hibernate QL rather than SQL)
         * because there are some difference between transition data from DB to POJO objects using JDBC and Hibernate*
         **/

        //current query goes to Hibernate session. After that the query will be converted to SQL-query and goes to DB
        Query<User> query = currentSession().createQuery("from User", User.class);
        List<User> users = query.list();
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        Query<User> q = currentSession().createQuery("from User where email = :email", User.class);
        q.setParameter("email", email);

        //the same hack to avoid exception in case when the result is empty
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        currentSession().save(user);
    }
}
