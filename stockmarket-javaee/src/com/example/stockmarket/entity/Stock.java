package com.example.stockmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.validation.StockSymbol;

// Entity -> Identity : JPA -> ORM
// JPA Provider -> Hibernate, EclipseLink (RI), OpenJPA, ...
// Mapping -> JPA @nnotation, Convention over Configuration, Configuration by exception
@Entity // must
@Table(name= "stocks")
@NamedQueries({
	// JPQL (JPa Query Language)
	@NamedQuery(name="Stock.findAll", query = "select s from Stock s"),
	@NamedQuery(name="Stock.findByCompany", query = "select s from Stock s where s.company=:company")
})
public class Stock {
	@Id // must
	@StockSymbol
	private String symbol; // identity
	@NotNull
	private String description;
	@NotNull
	private String company;
	@Column(name="fiyat")
	@Min(1)
	private double price;
	
	public Stock() {
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
		return "Stock [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price=" + price
				+ "]";
	}
	
}
