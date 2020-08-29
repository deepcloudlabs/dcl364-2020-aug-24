package com.example.imdb.dto;

public class Ticker {
	private String symbol;
	private String price;

	public Ticker() {
		super();
	}

	public Ticker(String symbol, String price) {
		super();
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticker [symbol=" + symbol + ", price=" + price + "]";
	}

}
