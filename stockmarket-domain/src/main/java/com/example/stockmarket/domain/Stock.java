package com.example.stockmarket.domain;

// DDD: Entity Root -> Aggregate
public class Stock {
	private final Symbol symbol;
	private final Company company;
	private Description decription;
	private Price price;

	public Stock(Symbol symbol, Company company) {
		this(symbol, company, new Description("no description"), new Price(10, Currency.TL));
	}

	public Stock(Symbol symbol, Company company, Description decription, Price price) {
		this.symbol = symbol;
		this.company = company;
		this.decription = decription;
		this.price = price;
	}

	public Description getDecription() {
		return decription;
	}

	public void setDecription(Description decription) {
		this.decription = decription;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public Company getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", company=" + company + ", decription=" + decription + ", price=" + price
				+ "]";
	}

}
