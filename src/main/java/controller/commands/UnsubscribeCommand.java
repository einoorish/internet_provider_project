package controller.commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.Fields;
import constants.URL;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class UnsubscribeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
 
        long tariffId = Long.valueOf(request.getParameter(Fields.SUBSCRIPTION_TARIFF_ID));

        UserDao userDao = new UserDaoImpl();
        userDao.unsubscribe(user.getId(), tariffId);
    	return URL.REDIRECT_PROFILE;
    }
}
