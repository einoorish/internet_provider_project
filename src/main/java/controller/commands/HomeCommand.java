package controller.commands;

import javax.servlet.http.HttpServletRequest;

import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.TariffType;

public class HomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	TariffDao dao = new TariffDaoImpl();
	    TariffType type;
	    if(request.getParameter("menutype") == null)
	    	type = TariffType.PHONE;
	    else type = TariffType.valueOf(request.getParameter("menutype").toString());
	   
	    request.setAttribute("type", type);
	    request.setAttribute("data", dao.getTariffListByType(type));

		return URL.HOME;
    }

}
