package controller.commands;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.URL;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);

        if (user != null && password.equals(user.getPassword())) {
        	System.out.println("Successfully loged in");
            session.setAttribute("user", user);
        }
        return URL.REDIRECT_HOME;
    }
}
