package model;

import java.sql.Date;
import java.time.LocalDate;

public class Subscription {
	private final long id;
	private final long userId;
    private final long tariffId;
    private final Date startDate;
    private final Date endDate;
    private SubscriptionStatus status;
    
	public Subscription(long id, long userId, long tariffId, Date startDate, Date endDate,
			String string) {
		this.id = id;
		this.userId = userId;
		this.tariffId = tariffId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = SubscriptionStatus.valueOf(string);
	}
	
	public long getId() {
		return id;
	}
	
	public SubscriptionStatus getStatus() {
		return status;
	}
	public void setStatus(SubscriptionStatus status) {
		this.status = status;
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
