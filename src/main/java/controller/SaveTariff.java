package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;
import model.TariffType;

public class SaveTariff extends HttpServlet {
	private static final long serialVersionUID = -5577566949314653715L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TariffDao dao = new TariffDaoImpl();
		if(request.getParameter("remove")!=null) {
			dao.delete(Long.valueOf(request.getParameter("remove")));
		} 
		response.sendRedirect(URL.REDIRECT_HOME);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TariffDao dao = new TariffDaoImpl();

		//TODO: use const values
		if(request.getParameter("id")!=null) {
			long id = Long.valueOf(request.getParameter("id"));
			String title = request.getParameter("title");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			TariffType type = TariffType.valueOf(request.getParameter("type"));
			String description = request.getParameter("description");	

			dao.update(new Tariff(id, title, type, price, description));
        } else {
    		String title = request.getParameter("title");
    		BigDecimal price = new BigDecimal(request.getParameter("price"));
    		TariffType type = TariffType.valueOf(request.getParameter("type"));
    		String description = request.getParameter("description");	

    		dao.add(new Tariff(title, type, price, description));
        }
		doGet(request, response);
	}

	
}
