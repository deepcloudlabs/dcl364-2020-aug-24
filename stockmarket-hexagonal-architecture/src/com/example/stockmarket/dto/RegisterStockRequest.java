package com.example.stockmarket.dto;

import com.example.stockmarket.domain.Currency;

public class RegisterStockRequest {

	private String symbol;
	private String url;
	private String companyName;
	private String description;
	private double price;
	private Currency currency;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "RegisterStockRequest [symbol=" + symbol + ", url=" + url + ", companyName=" + companyName
				+ ", description=" + description + ", price=" + price + ", currency=" + currency + "]";
	}

}
