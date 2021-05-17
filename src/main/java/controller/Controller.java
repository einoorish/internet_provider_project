package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.commands.Command;
import controller.commands.CommandHandler;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = -2670825831853246200L;
    private final static Logger LOG = Logger.getLogger(Controller.class.getSimpleName());
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = CommandHandler.get(commandName);
		String uri = command.execute(request);
		
		if (uri.contains("/controller")) {
			LOG.debug("Redirect: "+uri);
			response.sendRedirect(uri);
        } else {
			LOG.debug("Forward: "+uri);
            request.getRequestDispatcher(uri).forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		doGet(request, response);
	}
	
}
