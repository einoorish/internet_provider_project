package controller.commands;

import javax.servlet.http.HttpServletRequest;

import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;

public class EditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		TariffDao dao = new TariffDaoImpl();
		Tariff tariff = dao.getTariffById(Integer.valueOf(request.getParameter("tariffId")));
		
		request.setAttribute("tariff", tariff);

		return URL.TARIFF;
	}

}
