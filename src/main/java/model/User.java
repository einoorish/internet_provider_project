package model;

import java.math.BigDecimal;

public class User {
    private long id;
    private String login;
    private String password;
    private UserRole role;
    private UserStatus status;
    private BigDecimal funds;
    
    public User(String login, String password) {
    	this.login = login;
    	this.password = password;
    	role = UserRole.USER;
    	status = UserStatus.BLOCKED;
    	funds = new BigDecimal(0);
    };
    
    public User(long id, String login, String password, BigDecimal funds, UserRole role) {
    	this.id = id;
    	this.login = login;
    	this.password = password;
    	this.funds = funds;
    	this.role = role;
    }
    
    public User(long id, String login, String password, BigDecimal funds, UserRole role, UserStatus status) {
    	this.id = id;
    	this.login = login;
    	this.password = password;
    	this.funds = funds;
    	this.role = role;
    }
    public void setStatus(UserStatus status) {
    	this.status = status;
    }
    
    public UserStatus getStatus() {
    	return status;
    }
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public BigDecimal getFunds() {
		return funds;
	}
	
	public void setFunds(BigDecimal funds) {
		this.funds = funds;
	}
    
}
