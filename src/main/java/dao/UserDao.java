package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

//TODO: separate out the implementation

public interface UserDao {

    long register(User user);
    User getUserByLogin(String login);

}
