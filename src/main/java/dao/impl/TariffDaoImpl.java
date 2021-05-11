package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tariff;
import model.TariffType;

public class TariffDaoImpl implements dao.TariffDao {

	public int add(Tariff tariff) {
		String INSERT_QUERY = "INSERT INTO tariff" +
	            "  (id, title, type, price, description) VALUES " +
	            " (?, ?, ?, ?);";
		
		int result = 0;

        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
		}

        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, tariff.getTitle());
            preparedStatement.setString(3, tariff.getType().toString());
            preparedStatement.setString(4, tariff.getPrice().toString());
            preparedStatement.setString(5, tariff.getDescription().toString());

            System.out.println(preparedStatement);
            // Execute or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
        	System.err.println("Message: " + e.getMessage());
        }
        return result;
	}
	
	public Tariff getTariffById(long id) {
		Tariff tariff = null;
		
        try (Connection connection = DBManager.getInstance().getConnection()){   	
        	String SELECT_QUERY ="SELECT * FROM tariff WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            tariff = getTariff(resultSet);
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
        	System.err.println("Message: " + e.getMessage());
        }
        return tariff;
	}
	
	public List<Tariff> getTariffListByType(TariffType type) {
		List<Tariff> tariffs = null;

        try (Connection connection = DBManager.getInstance().getConnection()){
        	String SELECT_QUERY ="SELECT * FROM tariff WHERE type=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, type.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            tariffs = getTariffList(resultSet);
            
            resultSet.close();

            connection.commit();
        } catch (SQLException e) {
        	System.err.println("Message: " + e.getMessage());
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
