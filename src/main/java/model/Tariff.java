package model;

import java.math.BigDecimal;

public class Tariff {
	private long id;
	private String title;
	private TariffType type;
	private BigDecimal price;
	private String description;
	
	public Tariff(String title, TariffType type, BigDecimal price, String description){
		this.title = title;
		this.type = type;
		this.price = price;
		this.description = description;
	}
	
	public Tariff(long id, String title, TariffType type, BigDecimal price, String description){
		this.id = id;
		this.title = title;
		this.type = type;
		this.price = price;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public TariffType getType() {
		return type;
	}
	public void setType(TariffType type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return id+"[\n"
				+ "\tTitle: " + title
				+ "\n\tType: " + type.name()
				+ "\n\tPrice: " + price.toPlainString()
				+ "\n\tDescription: " + description
				+ "\n]";
	}
	
	
	

}
