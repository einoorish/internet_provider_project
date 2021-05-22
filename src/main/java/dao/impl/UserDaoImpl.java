package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import constants.Fields;
import constants.Queries;
import dao.UserDao;
import model.Subscription;
import model.SubscriptionStatus;
import model.User;
import model.UserRole;

public class UserDaoImpl implements UserDao {
    private final static Logger LOG = Logger.getLogger(UserDaoImpl.class.getSimpleName());

	public long register(User user) {
		LOG.info("REGISTER method started");
		int result = 0;
		
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_INSERT);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFunds().toString());
            preparedStatement.setString(4, user.getRole().toString());
    		LOG.debug(preparedStatement.toString());
    		
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
        	LOG.error(e.getMessage());
        }
        return result;
	}

	@Override
    public User getUserByLogin(String login) {
		LOG.info("GET USER BY LOGIN method started");
        User user = null;
        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_GET_BY_LOGIN);
            preparedStatement.setString(1, login);

    		LOG.debug(preparedStatement.toString());
            user = getUserFromPreparedStatement(preparedStatement);

            connection.commit();
            
        } catch (SQLException e) {
        	LOG.error(e.getMessage());
        }
        return user;
    }

	@Override
	public List<Subscription> getUserSubscriptions(long id) {
		LOG.info("GET USER SUBSCRIPTIONS method started");
		List<Subscription> subscriptions = new ArrayList<>();
		try (Connection connection = DBManager.getInstance().getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_GET_SUBSCRIPTIONS)) {
	            preparedStatement.setLong(1, id);
	    		LOG.debug(preparedStatement.toString());

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
	            
	        } catch (SQLException e) {
	        	LOG.error(e.getMessage());
        	}
		return subscriptions;
	}
	
	public void subscribe(long userId, long tariffId, Date startDate, Date endDate) {
		LOG.info("SUBSCRIBE method started");
		try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_SUBSCRIBE); 
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, tariffId);
            preparedStatement.setObject(3, startDate);
            preparedStatement.setObject(4, endDate);
            preparedStatement.setString(5, SubscriptionStatus.REQUESTED.toString());
    		LOG.debug(preparedStatement.toString());
    		
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        	LOG.error(e.getMessage());
        }
	}
	
	public void unsubscribe(long userId, long tariffId) {
		LOG.info("SUBSCRIBE method started");
		try (Connection connection = DBManager.getInstance().getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_UNSUBSCRIBE)) {
	            preparedStatement.setLong(1, userId);
	            preparedStatement.setLong(2, tariffId);
	    		LOG.debug(preparedStatement.toString());

	            preparedStatement.executeUpdate();
	            
        } catch (SQLException e) {
	        	LOG.error(e.getMessage());
        }
	}
	
	// -------  UTILS  --------
	
	private User getUserFromPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        User user = null;
        try(ResultSet resultSet = preparedStatement.executeQuery()){
        	if (resultSet.next()) {
                user = new User(resultSet.getLong(Fields.USER_ID), resultSet.getString(Fields.USER_LOGIN), 
                		    resultSet.getString(Fields.USER_PASSWORD), resultSet.getBigDecimal(Fields.USER_FUNDS), 
                		    UserRole.valueOf(resultSet.getString(Fields.USER_ROLE)));
            }
        }
        return user;
    }
}