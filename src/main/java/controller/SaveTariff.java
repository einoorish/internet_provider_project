package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.URL;
import controller.commands.Command;
import controller.commands.CommandHandler;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;
import model.TariffType;

public class SaveTariff extends HttpServlet {
	private static final long serialVersionUID = -5577566949314653715L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(URL.REDIRECT_HOME);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TariffDao dao = new TariffDaoImpl();
		
		//TODO: use const values
		long id = Long.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		TariffType type = TariffType.valueOf(request.getParameter("type"));
		String description = request.getParameter("description");

		dao.updateTariff(new Tariff(id, title, type, price, description));
		doGet(request, response);
	}

	
}
