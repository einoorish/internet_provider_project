package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import constants.URL;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class RegistrationCommand implements Command {
    private final static Logger LOG = Logger.getLogger(RegistrationCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
    	
    	UserDao userDao = new UserDaoImpl();
    	
    	User user = new User(login, password);
    	System.out.println(login);

		userDao.register(user);
        session.setAttribute("user", user);
		
        LOG.info("User "+ login + " was registered");
    	return URL.REDIRECT_HOME;	
    }
}
