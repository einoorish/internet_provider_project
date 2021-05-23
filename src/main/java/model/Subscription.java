package model;

import java.sql.Date;
import java.time.LocalDate;

public class Subscription {
	private final long id;
	private final long userId;
    private final long tariffId;
    private final Date startDate;
    private final Date endDate;
    
	public Subscription(long id, long userId, long tariffId, Date startDate, Date endDate) {
		this.id = id;
		this.userId = userId;
		this.tariffId = tariffId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public long getId() {
		return id;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public long getTariffId() {
		return tariffId;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
    
    
}
