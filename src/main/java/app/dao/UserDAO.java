package app.dao;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
//        List<User> allUsers = jdbcTemplate.query("select * from users", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int i) throws SQLException {
//                User user = new User();
//                user.setName(rs.getString("name"));
//                user.setSurname(rs.getString("surname"));
//                user.setEmail(rs.getString("email"));
//                return user;
//            }
//        });

        //OR... (if the names of POJO are the same as the names of DB columns (as in our case)
        List<User> allUsers = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));

        return allUsers;
    }

    //NOTE: if queryForObject doesn't return result - it throws exception!
    //that's why we can catch this exception or use queryForList
    public User getUserByEmail(String email) {
        User user = jdbcTemplate.query("select * from users where EMAIL = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);

        return user;
    }

    public void addUser(User user) {
        jdbcTemplate.update("insert into users values (?, ?, ?)", user.getName(), user.getSurname(), user.getEmail());
    }
}