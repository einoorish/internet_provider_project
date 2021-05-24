package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import constants.Regex;
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
        
        if(!login.matches(Regex.LOGIN_REGEX) || !password.matches(Regex.PASSWORD_REGEX)) {
            LOG.info("Did not match regex");
        	session.setAttribute("invalidData", "true");
        	return URL.REDIRECT_HOME;	
        }
    	session.setAttribute("invalidData", "false");
        
    	UserDao userDao = new UserDaoImpl();
    	
    	User user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()));
    	System.out.println(login);

		userDao.register(user);
        session.setAttribute("user", user);
		
        LOG.info("User "+ login + " was registered");
    	return URL.REDIRECT_HOME;	
    }
}
