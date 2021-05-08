package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserRole;
import model.User;

@WebServlet("/register")
public class UserRegistrationForm extends HttpServlet {
	private static final long serialVersionUID = -5480318454519187196L;
	private UserDao userDao = new UserDao();
       
    public UserRegistrationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		System.out.println(request.getHeader(getServletInfo()));
		System.out.println(request.getHeader(getServletName()));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setFunds(new BigDecimal(0));
		user.setRole(UserRole.USER);

		userDao.register(user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userdetails.jsp");
		dispatcher.forward(request, response);
	}

}
