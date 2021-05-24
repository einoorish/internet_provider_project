package controller.commands;

import java.io.FileWriter;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import constants.URL;
import dao.TariffDao;
import dao.impl.TariffDaoImpl;
import model.Tariff;

public class DownloadCommand implements Command {
    private final static Logger LOG = Logger.getLogger(DownloadCommand.class.getSimpleName());

	@Override
	public String execute(HttpServletRequest request) {
		LOG.info("DOWNLOAD started");
		request.getSession().setAttribute("invalidData", "false");
        String sort= request.getParameter("sort");
        TariffDao tariffDao = new TariffDaoImpl();
        
        List<Tariff> tariffs = tariffDao.getSortedTariffList(sort);  
        
        int maxTitleLength = findMaxTitleLength(tariffs);
        int maxDescriptionLength = findMaxDescriptionLength(tariffs);
	
        int i = tariffs.size();
        LOG.debug(i + " records ordered by "+sort);
        while(i-- >= 0) { 
        	//TODO: fix description overflow
         
	        FileWriter fw;
			try {
				fw = new FileWriter("../downloads/data.txt", false);
				fw.write("+--------+"+StringUtils.repeat("-", maxTitleLength+7)+"+--------------+--------------+"+StringUtils.repeat("-", maxDescriptionLength+12)+"+\n");
		        fw.write("| ID     | Title "+StringUtils.repeat(" ", maxTitleLength)+"| Price        | Type         | Description"+StringUtils.repeat(" ", maxDescriptionLength)+"|\n");
		        fw.write("+--------+"+StringUtils.repeat("-", maxTitleLength+7)+"+--------------+--------------+"+StringUtils.repeat("-", maxDescriptionLength+12)+"+\n");

		        Iterator<Tariff> it = tariffs.iterator();
		        LOG.debug("writing " + it.toString());
		        while(it.hasNext()){
		            Tariff sf = it.next();
		            fw.write("| "+String.format("%-6s", sf.getId())+" | "+String.format("%-"+(maxTitleLength+6)+"s", sf.getTitle())+"| "
		            + String.format("%-13s", sf.getPrice())+"| " + String.format("%-13s", sf.getType())+"| "
		            +String.format("%-"+(maxDescriptionLength+9)+"s", sf.getDescription())+ "  |\n");
		            fw.write((it.hasNext())
		                    ?"|--------|"+StringUtils.repeat("-", maxTitleLength+7)+"|--------------|--------------|"+StringUtils.repeat("-", maxDescriptionLength+12)+"|\n"
		                    :"+--------+"+StringUtils.repeat("-", maxTitleLength+7)+"+--------------+--------------+"+StringUtils.repeat("-", maxDescriptionLength+12)+"+\n");
		        }
		        fw.close();
			} catch (IOException e) {
            	LOG.error(e.getMessage());
			}
	
        }
        
		return URL.REDIRECT_HOME;
	}

	private int findMaxDescriptionLength(List<Tariff> tariffs) {
		Comparator<Tariff> comp = (Tariff o1, Tariff o2) -> Integer.compare(o2.getDescription().length(),o1.getDescription().length());
		Collections.sort(tariffs, comp);
		return tariffs.get(0).getDescription().length();
	}

	private int findMaxTitleLength(List<Tariff> tariffs) {
		Comparator<Tariff> comp = (Tariff o1, Tariff o2) -> Integer.compare(o2.getTitle().length(), o1.getTitle().length());
		Collections.sort(tariffs, comp);
		return tariffs.get(0).getTitle().length();
	}

}
