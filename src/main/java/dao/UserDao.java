package dao;

import java.util.List;

import model.Subscription;
import model.User;

public interface UserDao {

    long register(User user);
    User getUserByLogin(String login);
	List<Subscription> getUserSubscriptions(long id);

}
