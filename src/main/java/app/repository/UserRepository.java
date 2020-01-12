package app.repository;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

//indeed it is like @Component
@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {
    //BigDecimal is type of Primary Key

    /**
     * not necessary to write own implementation
     * Spring Data JPA will find these interface and use SimpleJpaRepository (basic implementation of JpaRepository)
     */

    //query method that will be translated (somehow!) to the real workable method
    User findByEmail(String email);
}
