package controller.commands;

import javax.servlet.http.HttpServletRequest;

import constants.URL;

public class MyProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
		return URL.REDIRECT_HOME;
    	
    }
}
