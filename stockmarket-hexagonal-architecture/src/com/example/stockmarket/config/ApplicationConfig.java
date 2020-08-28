package com.example.stockmarket.config;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.stockmarket.application.StandardStockApplication;
import com.example.stockmarket.application.StockApplication;
import com.example.stockmarket.infra.EventPublisher;
import com.example.stockmarket.repository.StockRepository;

@Named
@Singleton
public class ApplicationConfig {
	@Inject private StockRepository stockRepository;
	@Inject private EventPublisher eventPublisher;
	
	@Named
	@Singleton
	@Produces
	public StockApplication create() {
		return new StandardStockApplication(stockRepository, eventPublisher);
	}
}
