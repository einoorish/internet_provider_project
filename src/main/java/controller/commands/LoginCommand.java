package controller.commands;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import constants.Fields;
import constants.URL;

public class LoginCommand implements Command {
    private final static Logger LOG = Logger.getLogger(LoginCommand.class.getSimpleName());
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String login = request.getParameter(Fields.USER_LOGIN);
        String password = request.getParameter(Fields.USER_PASSWORD);

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);

        if (user != null && password.equals(user.getPassword())) {
        	LOG.info("Successful log in");
            session.setAttribute("user", user);
        } else {
        	LOG.info("Wrong credentials");
        }
        return URL.REDIRECT_HOME;
    }
}
