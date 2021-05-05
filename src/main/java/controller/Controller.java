package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.TariffType;

@WebServlet("/home")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = -1951358628804251994L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    TariffDao dao = new TariffDaoImpl();
	    TariffType type = TariffType.PHONE;
	   
	    request.setAttribute("type", type);
	    request.setAttribute("data", dao.getTariffListByType(type));

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request, response);
	}
	
}
