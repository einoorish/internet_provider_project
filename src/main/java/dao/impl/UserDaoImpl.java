package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.Fields;
import dao.UserDao;
import model.Subscription;
import model.User;
import model.UserRole;

public class UserDaoImpl implements UserDao {

	public long register(User user) {
		String INSERT_QUERY = "INSERT INTO user" +
	            "  (login, password, funds, role) VALUES " +
	            " (?, ?, ?, ?);";
		
		int result = 0;

        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
		}

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/internet_provider?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFunds().toString());
            preparedStatement.setString(4, user.getRole().toString());

            System.out.println(preparedStatement);
            // Execute or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
        	System.err.println("Message: " + e.getMessage());
        }
        return result;
	}

	@Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login=?")) {
            preparedStatement.setString(1, login);
            
            user = getUserFromPreparedStatement(preparedStatement);

            connection.commit();
        } catch (SQLException ex) {
        }
        return user;
    }
	
	private User getUserFromPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        User user = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	//TODO: use builder pattern?
            user = new User(resultSet.getLong(Fields.USER_ID), resultSet.getString(Fields.USER_LOGIN), 
            		    resultSet.getString(Fields.USER_PASSWORD), resultSet.getBigDecimal(Fields.USER_FUNDS), 
            		    UserRole.valueOf(resultSet.getString(Fields.USER_ROLE)));
        }
        resultSet.close();
        return user;
    }

	@Override
	public List<Subscription> getUserSubscriptions(long id) {
		List<Subscription> subscriptions = new ArrayList<>();
		try (Connection connection = DBManager.getInstance().getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subscription WHERE userId=?")) {
	            preparedStatement.setLong(1, id);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                subscriptions.add(
	                		new Subscription(resultSet.getLong(Fields.SUBSCRIPTION_ID), resultSet.getLong(Fields.SUBSCRIPTION_USER_ID), 
	                		    resultSet.getLong(Fields.SUBSCRIPTION_TARIFF_ID), resultSet.getDate(Fields.SUBSCRIPTION_START_DATE),
	                		    resultSet.getDate(Fields.SUBSCRIPTION_END_DATE), resultSet.getString(Fields.SUBSCRIPTION_STATUS))
	                		);
	            }
	            resultSet.close();

	            connection.commit();
	        } catch (SQLException ex) {
        }
		
		System.out.println(subscriptions.size());
		return subscriptions;
	}
	
}