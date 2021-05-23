package controller.commands;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import constants.URL;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class AddFundsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDao dao = new UserDaoImpl();
		User user = (User)request.getSession().getAttribute("user");
		BigDecimal fundsToAdd = new BigDecimal(request.getParameter("funds"));
		user.setFunds(user.getFunds().add(fundsToAdd));
		dao.addFunds(user.getId(), fundsToAdd);
		return URL.REDIRECT_PROFILE;
		
		
	}

}
