package controller.commands;

import javax.servlet.http.HttpServletRequest;

import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Subscription;
import model.Tariff;
import model.TariffType;

public class DownloadCommand implements Command {

    public String execute(HttpServletRequest request) {
        String type = request.getParameter("tariffType");
        String sort= request.getParameter("sort");

        TariffDao tariffDao = new TariffDaoImpl();
        List<Tariff> tariffs = tariffDao.getTariffListByType(TariffType.valueOf(type), sort);
        
        //write to file?
        
        return URL.REDIRECT_HOME;
    }
}
