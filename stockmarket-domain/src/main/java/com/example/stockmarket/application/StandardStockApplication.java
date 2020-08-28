package com.example.stockmarket.application;

import java.util.List;

import com.example.stockmarket.domain.Company;
import com.example.stockmarket.domain.Currency;
import com.example.stockmarket.domain.Description;
import com.example.stockmarket.domain.Price;
import com.example.stockmarket.domain.Stock;
import com.example.stockmarket.domain.Symbol;
import com.example.stockmarket.repository.StockRepository;

public class StandardStockApplication implements StockApplication {
	private StockRepository stockRepository;
	
	public StandardStockApplication(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public Stock registerCompany(String symbol, String url, String companyName, String description, double price,
			Currency currency) {
		var company = new Company(url, companyName);
		var stock = new Stock(Symbol.of(symbol), company, new Description(description), new Price(price, currency));
		// call business methods in stock "aggregate"		
		stockRepository.createStock(stock);
		return stock;
	}

	@Override
	public List<Stock> unregisterCompany(String url) {
		var stocks = stockRepository.findByCompanyUrl(url);
		for (var stock : stocks)
			stockRepository.removeStock(stock.getSymbol());
		return stocks;
	}

}
