package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Tariff;
import model.TariffType;

public class TariffDaoImpl implements dao.TariffDao {
    private final static Logger LOG = Logger.getLogger(TariffDaoImpl.class.getSimpleName());

	public int add(Tariff tariff) {
		LOG.info("ADD method started");
		String INSERT_QUERY = "INSERT INTO tariff" +
	            "  (title, type, price, description) VALUES " +
	            " (?, ?, ?, ?);";
		 
		int result = 0;

        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
		}

        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, tariff.getTitle());
            preparedStatement.setString(2, tariff.getType().toString());
            preparedStatement.setString(3, tariff.getPrice().toString());
            preparedStatement.setString(4, tariff.getDescription().toString());

            System.out.println(preparedStatement);
            LOG.debug("QUERY: "+preparedStatement.toString());
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return result;
	}
	
	public void update(Tariff tariff) {
		LOG.info("UPDATE method started");
		final String UPDATE_QUERY = "UPDATE tariff SET title=?, type=?, price=?, description=?  WHERE id=?;";

        try (Connection connection = DBManager.getInstance().getConnection()){   

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, tariff.getTitle());
            preparedStatement.setString(2, tariff.getType().toString());
            preparedStatement.setString(3, tariff.getPrice().toPlainString());
            preparedStatement.setString(4, tariff.getDescription());
            preparedStatement.setLong(5, tariff.getId());
            LOG.debug("QUERY: "+preparedStatement.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
		}
	}
	
	public void delete(long id) {
		LOG.info("DELETE method started");
		final String DELETE_QUERY = "DELETE FROM tariff WHERE id=?;";

        try (Connection connection = DBManager.getInstance().getConnection()){   
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOG.debug("QUERY: "+preparedStatement.toString());
        } catch (SQLException e) {
            LOG.error(e.getMessage());
		}
	}

	
	public Tariff getTariffById(long id) {
		Tariff tariff = null;
		
        try (Connection connection = DBManager.getInstance().getConnection()){   	
        	String SELECT_QUERY ="SELECT * FROM tariff WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
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
        	String SELECT_QUERY ="SELECT * FROM tariff WHERE type=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
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
        
    //TODO: move to utils?
    private List<Tariff> getTariffList(ResultSet resultSet) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (resultSet.next()) {
        	//TODO: extract db field names to another file
        	Tariff tariff = new Tariff(resultSet.getLong("id"), 
        								resultSet.getString("title"),
        								TariffType.valueOf(resultSet.getString("type")),
        								resultSet.getBigDecimal("price"),
        								resultSet.getString("description"));
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
