package dao;

import java.util.Date;
import java.util.List;

import model.Subscription;
import model.User;

public interface UserDao {

    long register(User user);
    User getUserByLogin(String login);
	List<Subscription> getUserSubscriptions(long id);
	public void subscribe(long userId, long tariffId, Date startDate, Date endDate);
	public void unsubscribe(long userId, long tariffId);
}
