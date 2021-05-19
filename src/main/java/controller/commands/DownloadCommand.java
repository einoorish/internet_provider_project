package controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String sort= request.getParameter("sort");
        TariffDao tariffDao = new TariffDaoImpl();
        
        List<Tariff> tariffs = tariffDao.getSortedTariffList(sort);  
	
        int i = tariffs.size();
        LOG.debug(i + " records ordered by "+sort);
        while(i-- >= 0) { 
        	//TODO: fix description overflow
         
	        FileWriter fw;
			try {
				fw = new FileWriter("../downloads/data.txt", false);
				fw.write("+--------+----------------------+--------------+--------------+------------------------+\n");
		        fw.write("| ID     | Title                | Price        | Type         | Description            |\n");
		        fw.write("+--------+----------------------+--------------+--------------+------------------------+\n");

		        Iterator<Tariff> it = tariffs.iterator();
		        LOG.debug("writing " + it.toString());
		        while(it.hasNext()){
		            Tariff sf = it.next();
		            fw.write("| "+String.format("%-6s", sf.getId())+" | "+String.format("%-21s", sf.getTitle())+"| "
		            + String.format("%-13s", sf.getPrice())+"| " + String.format("%-13s", sf.getType())+"| "
		            +String.format("%-30s", sf.getDescription())+ "  |\n");
		            fw.write((it.hasNext())
		                    ?"|--------|----------------------|--------------|--------------|------------------------|\n"
		                    :"+--------+----------------------+--------------+--------------+------------------------+\n");
		        }
		        fw.close();
			} catch (IOException e) {
            	LOG.error(e.getMessage());
			}
	
        }
        
		return URL.REDIRECT_HOME;
	}

}
