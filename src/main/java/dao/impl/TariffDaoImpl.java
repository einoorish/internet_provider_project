package dao.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import constants.Queries;
import model.Tariff;
import model.TariffType;

public class TariffDaoImpl implements dao.TariffDao {
    private final static Logger LOG = Logger.getLogger(TariffDaoImpl.class.getSimpleName());

	public int insert(Tariff tariff) {
		LOG.info("INSERT method started");
		int result = 0;

        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.TARIFF_INSERT)) {
            preparedStatement.setString(1, tariff.getTitle());
            preparedStatement.setString(2, tariff.getTitle_uk());
            preparedStatement.setString(3, tariff.getType().toString());
            preparedStatement.setString(4, tariff.getPrice().toString());
            preparedStatement.setString(5, tariff.getDescription());
            preparedStatement.setString(6, tariff.getDescription_uk());

            LOG.debug("QUERY: "+preparedStatement.toString());
            result = preparedStatement.executeUpdate();
          
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return result;
	}
	
	public void update(Tariff tariff) {
		LOG.info("UPDATE method started");

        try (Connection connection = DBManager.getInstance().getConnection()){   

            PreparedStatement preparedStatement = connection.prepareStatement(Queries.TARIFF_UPDATE);
            preparedStatement.setString(1, tariff.getTitle());
            preparedStatement.setString(2, tariff.getTitle_uk());
            preparedStatement.setString(3, tariff.getType().toString());
            preparedStatement.setString(4, tariff.getPrice().toString());
            preparedStatement.setString(5, tariff.getDescription());
            preparedStatement.setString(6, tariff.getDescription_uk());
            preparedStatement.setLong(7, tariff.getId());
            
            LOG.debug("QUERY: "+preparedStatement.toString());
            preparedStatement.executeUpdate();
        
        } catch (SQLException e) {
            LOG.error(e.getMessage());
		}
	}
	
	public void delete(long id) {
		LOG.info("DELETE method started");

        try (Connection connection = DBManager.getInstance().getConnection()){   
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.TARIFF_DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOG.debug("QUERY: "+preparedStatement.toString());
        } catch (SQLException e) {
            LOG.error(e.getMessage());
		}
	}

	
	public Tariff getTariffById(long id) {
		LOG.info("GET TARIFF BY ID method started");
		Tariff tariff = null;
		
        try (Connection connection = DBManager.getInstance().getConnection()){   	
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.TARIFF_GET_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            LOG.debug("QUERY: "+preparedStatement.toString());
            tariff = getTariff(resultSet);
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return tariff;
	}
	
	public List<Tariff> getTariffListByType(TariffType type) {
		LOG.info("GET TARIFF BY TYPE method started");
		List<Tariff> tariffs = null;

        try (Connection connection = DBManager.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.TARIFF_GET_BY_TYPE);
            preparedStatement.setString(1, type.toString());
            LOG.debug("QUERY: "+preparedStatement.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            tariffs = getTariffList(resultSet);
            
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return tariffs;
	}
	
	public List<Tariff> getSortedTariffList(String sort) {
		LOG.info("GET SORTED TARIFFS method started");
		List<Tariff> tariffs = null;

        try (Connection connection = DBManager.getInstance().getConnection()){
        	
        	//using preparedStatement would add extra quotes which will result in incorrect results
        	String SELECT_QUERY ="SELECT * FROM tariff ORDER BY "+sort+" ASC;"; 
            LOG.debug("QUERY: "+SELECT_QUERY);

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_QUERY);
            tariffs = getTariffList(resultSet);
            resultSet.close();
            connection.commit();

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return tariffs;
	}
        
    // -------  UTILS  ---------
	
    private List<Tariff> getTariffList(ResultSet resultSet) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (resultSet.next()) {
        	Tariff tariff = new Tariff(resultSet.getLong("id"), 
        								resultSet.getString("title"),
        								resultSet.getString("title_uk"),
        								TariffType.valueOf(resultSet.getString("type")),
        								resultSet.getBigDecimal("price"),
        								resultSet.getString("description"),
        								resultSet.getString("description_uk"));
        	tariffs.add(tariff);
        }
        return tariffs;
    }

    private Tariff getTariff(ResultSet resultSet) throws SQLException {
        List<Tariff> rooms = getTariffList(resultSet);
        if (!rooms.isEmpty()) return rooms.get(0);
        return null;
    }
        
}
