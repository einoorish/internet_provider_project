package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import constants.URL;

public class LogoutCommand implements Command {
    private final static Logger LOG = Logger.getLogger(LogoutCommand.class.getSimpleName());
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    	LOG.info("Logged out");
        return URL.REDIRECT_HOME;
    }
}
