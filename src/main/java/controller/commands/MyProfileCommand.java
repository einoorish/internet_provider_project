package controller.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.URL;
import dao.TariffDao;
import dao.UserDao;
import dao.impl.TariffDaoImpl;
import dao.impl.UserDaoImpl;
import model.Subscription;
import model.Tariff;
import model.User;

public class MyProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        UserDao userDao = new UserDaoImpl();
        List<Subscription> subscriptions = userDao.getUserSubscriptions(user.getId());
        
        TariffDao tariffDao = new TariffDaoImpl();
        List<Tariff> userTariffs = new ArrayList<>();
        for(Subscription subscription: subscriptions) {
        	userTariffs.add(tariffDao.getTariffById(subscription.getTariffId()));
        }
        
        request.setAttribute("subscriptions", userTariffs);
        
		return URL.PROFILE;
    	
    }
}
