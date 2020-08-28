package com.example.stockmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.example.stockmarket.domain.Currency;

@Entity
@Table(name = "microstocks")
@NamedQueries({
	@NamedQuery(name="StockEntity.findAll",query = "select s from StockEntity s"),
	@NamedQuery(name="StockEntity.ByCompanyUrl",query = "select s from StockEntity s where url=:url")
})
public class StockEntity {
	@Id
	private String symbol;
	private String description;
	@Column(name = "company_url")
	private String companyUrl;
	@Column(name = "company_name")
	private String companyname;
	private double price;
	@Enumerated(EnumType.STRING)
	private Currency currency;

	public StockEntity() {
	}

	public StockEntity(String symbol, String description, String companyUrl, String companyname, double price,
			Currency currency) {
		this.symbol = symbol;
		this.description = description;
		this.companyUrl = companyUrl;
		this.companyname = companyname;
		this.price = price;
		this.currency = currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "StockEntity [symbol=" + symbol + ", description=" + description + ", companyUrl=" + companyUrl
				+ ", companyname=" + companyname + ", price=" + price + ", currency=" + currency + "]";
	}

}
