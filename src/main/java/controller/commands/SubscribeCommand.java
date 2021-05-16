package controller.commands;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import constants.Fields;
import constants.URL;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class SubscribeCommand implements Command {

	private Date calculateEndDate() {
        Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, 1);
		return today.getTime();
	}
	
    @Override
    public String execute(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
 
        long tariffId = Long.valueOf(request.getParameter(Fields.SUBSCRIPTION_TARIFF_ID));
        
        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
        Date startDate = today.getTime();

        UserDao userDao = new UserDaoImpl();
        userDao.subscribe(user.getId(), tariffId, startDate, calculateEndDate());
    	return URL.REDIRECT_PROFILE;
    }
}
