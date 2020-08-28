package com.example.stockmarket.domain;

// Value
public class Price {
	private final double value;
	private final Currency currency;

	public Price(double value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

}
