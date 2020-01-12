package app.dao.impl;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/** annotation Transactional is required because all actions should be executed in transactions
 *  this annotation will be applied for all methods of current class (i.e. entity) if the annotation is not overriden for some particular method
 */
@Transactional(readOnly = true)
@Component
public class JpaUserDAO implements UserDAO {
    //instead of Autowired
    //NOTE: id should be 'entityManagerFactory' if we want use JpaRepository!
    @PersistenceContext(unitName = "entityManagerFactory")
    //this annotation says that current component (JpaUserDAO) depends on EntityManager (that is managed by spring-container of beans) and EN is related to its PersistenceContext
    //PersistenceContext is 'place' when we are working on our entity until we save the changes
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        //NOTE: JPA has its own language - JPQL !!!
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> q  = entityManager.createQuery("select  u from User u where u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }
}