package constants;

public class Queries {
	
	public static final String TARIFF_INSERT = "INSERT INTO tariff (title, type, price, description) VALUES (?, ?, ?, ?);";
	public static final String TARIFF_UPDATE = "UPDATE tariff SET title=?, type=?, price=?, description=?  WHERE id=?;";
	public static final String TARIFF_DELETE = "DELETE FROM tariff WHERE id=?;";
	public static final String TARIFF_GET_BY_ID = "SELECT * FROM tariff WHERE id=?;";
	public static final String TARIFF_GET_BY_TYPE = "SELECT * FROM tariff WHERE type=?";
	
	public static final String USER_INSERT = "INSERT INTO user (login, password, funds, role) VALUES (?, ?, ?, ?);";
	public static final String USER_GET_BY_LOGIN = "SELECT * FROM user WHERE login=?"; 
	public static final String USER_GET_SUBSCRIPTIONS = "SELECT * FROM subscription WHERE userId=?";
	public static final String USER_SUBSCRIBE = "INSERT INTO subscription(userId, tariffId, startDate, endDate, status) VALUES (?, ?, ?, ?, ?);";
	public static final String USER_UNSUBSCRIBE = "DELETE FROM subscription WHERE userId=? AND tariffId=?;";
}
    