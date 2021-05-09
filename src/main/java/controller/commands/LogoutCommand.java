package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.URL;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return URL.REDIRECT_HOME;
    }
}
