package controller.commands;

import javax.servlet.http.HttpServletRequest;

import constants.Fields;
import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;

public class EditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		TariffDao dao = new TariffDaoImpl();
		
		if(request.getParameter(Fields.TARIFF_ID)!=null) {
			Tariff tariff = dao.getTariffById(Integer.valueOf(request.getParameter(Fields.TARIFF_ID)));
			request.setAttribute("tariff", tariff);
		}

		return URL.TARIFF;
	}

}
