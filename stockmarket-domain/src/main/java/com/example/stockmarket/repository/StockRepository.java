package com.example.stockmarket.repository;

import java.util.List;
import java.util.Optional;

import com.example.stockmarket.domain.Stock;
import com.example.stockmarket.domain.Symbol;

public interface StockRepository {
	Optional<Stock> findBySymbol(Symbol symbol);

	List<Stock> find(int page, int size);

	void createStock(Stock stock);

	void saveStock(Stock stock);

	Optional<Stock> removeStock(Symbol symbol);

	List<Stock> findByCompanyUrl(String url);
}
