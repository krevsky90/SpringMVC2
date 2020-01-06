package app.dao.impl;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class JdbcApiUserDAO implements UserDAO {
    private static Connection conn;

    static {
        String url = null;
        String username = null;
        String password = null;

        //load db properties
        try (InputStream io = UserDAO.class.getClassLoader().getResourceAsStream("oracle_persistense.properties")) {
            Properties properties = new Properties();
            properties.load(io);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //acquire DB connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("select * from users where EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            //use 'if' because email should be unique -> we return the only one user that has requested email
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users values (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
