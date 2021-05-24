package model;

import java.math.BigDecimal;

public class Tariff {
	private long id;
	private String title;
	private String title_uk;
	private TariffType type;
	private BigDecimal price;
	private String description;
	private String description_uk;
	
	public Tariff(String title, String title_uk, TariffType type, BigDecimal price, String description, String description_uk){
		this.title = title;
		this.title_uk = title_uk;
		this.type = type;
		this.price = price;
		this.description = description;
		this.description_uk = description_uk;
	}
	
	public Tariff(long id, String title, String title_uk, TariffType type, BigDecimal price, String description, String description_uk){
		this.id = id;
		this.title = title;
		this.title_uk = title_uk;
		this.type = type;
		this.price = price;
		this.description = description;
		this.description_uk = description_uk;
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
	
	public String getTitle_uk() {
		return title_uk;
	}

	public void setTitle_uk(String title_uk) {
		this.title_uk = title_uk;
	}

	public String getDescription_uk() {
		return description_uk;
	}

	public void setDescription_uk(String description_uk) {
		this.description_uk = description_uk;
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
