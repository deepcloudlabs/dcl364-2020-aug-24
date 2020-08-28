package com.example.stockmarket.service;

import static java.util.stream.Collectors.toList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.example.stockmarket.application.StockApplication;
import com.example.stockmarket.domain.Stock;
import com.example.stockmarket.domain.Symbol;
import com.example.stockmarket.dto.RegisterStockRequest;
import com.example.stockmarket.dto.RegisterStockResponse;
import com.example.stockmarket.dto.UnregisterCompanyRequest;
import com.example.stockmarket.dto.UnregisterCompanyResponse;

@Stateless
public class StockService {
	@Inject
	private StockApplication stockApplication;

	@Transactional
	public RegisterStockResponse register(RegisterStockRequest request) {
		var symbol = request.getSymbol();
		var url = request.getUrl();
		var companyName = request.getCompanyName();
		var description = request.getDescription();
		var price = request.getPrice();
		var currency = request.getCurrency();
		var stock = stockApplication.registerCompany(symbol, url, companyName, description, price, currency);
		return new RegisterStockResponse("success", stock.getSymbol().getValue());
	}

	@Transactional
	public UnregisterCompanyResponse unregister(UnregisterCompanyRequest request) {
		var url = request.getUrl();
		var stocks = stockApplication.unregisterCompany(url);
		var symbols = stocks.stream().map(Stock::getSymbol).map(Symbol::getValue).collect(toList());
		return new UnregisterCompanyResponse("success", url, symbols);
	}

}
