package app.service;

import app.model.User;

import java.util.List;

//Controller will use Service to interact with Persistence layer (it can be DAO or Repository or ...)
public interface UserService {
    List<User> getAllUsers();

    User getUserByEmail(String email);

    void addUser(User user);
}
