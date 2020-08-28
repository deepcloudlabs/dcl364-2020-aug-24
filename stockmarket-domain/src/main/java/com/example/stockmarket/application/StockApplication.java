package com.example.stockmarket.application;

import java.util.List;

import com.example.stockmarket.domain.Currency;
import com.example.stockmarket.domain.Stock;

public interface StockApplication {
	Stock registerCompany(String symbol, String url, String companyName, String description, double price, Currency currency);
	List<Stock> unregisterCompany(String url);
}
