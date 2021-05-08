package controller.commands;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String forward = URL.REDIRECT_HOME;

        request.setAttribute("usernameNotFound", login);
        if (user == null || password.equals(user.getPassword())) {
            request.setAttribute("isEnteredDataValid", "false");
            forward = URL.REDIRECT_LOGIN;
        } else {
        	System.out.println("Successfully loged in");
            session.setAttribute("user", user);
        }
        return forward;
    }
}
