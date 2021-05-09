package dao;


import model.User;

//TODO: separate out the implementation

public interface UserDao {

    long register(User user);
    User getUserByLogin(String login);

}
