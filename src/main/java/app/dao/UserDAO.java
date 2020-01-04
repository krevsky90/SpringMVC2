package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {
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

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
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

        return allUsers;
    }
}