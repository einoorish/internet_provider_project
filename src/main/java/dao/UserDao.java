package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

//TODO: separate out the implementation

public class UserDao {

	public int register(User user) {
		String INSERT_QUERY = "INSERT INTO user" +
	            "  (id, login, password, funds, role) VALUES " +
	            " (?, ?, ?, ?, ?);";
		
		int result = 0;

        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
		}

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/internet_provider?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFunds().toString());
            preparedStatement.setString(5, user.getRole().toString());

            System.out.println(preparedStatement);
            // Execute or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
        	System.err.println("Message: " + e.getMessage());
        }
        return result;
	}
	
}
