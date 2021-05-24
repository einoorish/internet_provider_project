package controller;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import constants.Fields;
import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;
import model.TariffType;
public class SaveTariff extends HttpServlet {
	private static final long serialVersionUID = -5577566949314653715L;
    private final static Logger LOG = Logger.getLogger(SaveTariff.class.getSimpleName());
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TariffDao dao = new TariffDaoImpl();
		if(request.getParameter("remove")!=null) {
			dao.delete(Long.valueOf(request.getParameter("remove")));
			LOG.debug("Done removing tariff");
		} 
		response.sendRedirect(URL.REDIRECT_HOME);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("POST started");
        
		TariffDao dao = new TariffDaoImpl();
		LOG.debug(request.getParameter(Fields.TARIFF_TITLE_UK));
		String title = request.getParameter(Fields.TARIFF_TITLE);
		String title_uk = request.getParameter(Fields.TARIFF_TITLE_UK);
		BigDecimal price = new BigDecimal(request.getParameter(Fields.TARIFF_PRICE));
		TariffType type = TariffType.valueOf(request.getParameter(Fields.TARIFF_TYPE));
		String description = request.getParameter(Fields.TARIFF_DESCRIPTION);	
		String description_uk = request.getParameter(Fields.TARIFF_DESCRIPTION_UK);	

		if(request.getParameter(Fields.TARIFF_ID)!=null) {
			long id = Long.valueOf(request.getParameter(Fields.TARIFF_ID));
			dao.update(new Tariff(id, title, title_uk, type, price, description, description_uk));
			LOG.debug("Done updating tariff");
        } else {
    		dao.insert(new Tariff(title, title_uk, type, price, description, description_uk));
			LOG.debug("Done adding tariff");
        }
		doGet(request, response);
	}

	
}
