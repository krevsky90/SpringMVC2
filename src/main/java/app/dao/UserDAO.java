package app.dao;

import app.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUserByEmail(String email);

    void addUser(User user);
}
