package com.example.stockmarket.service;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.lottery.repository.StockRepository;

@Stateless
public class StockService {
	@Inject private StockRepository stockRepo;
	
	@Schedule(second = "*/7", hour = "*", minute = "*")
	public void listStockPrices() {
		stockRepo.findAll().forEach(System.err::println);	
	}
}
