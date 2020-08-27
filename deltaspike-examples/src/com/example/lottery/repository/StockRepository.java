package com.example.lottery.repository;

import java.util.List;

import org.apache.deltaspike.data.api.Repository;

import com.example.stockmarket.entity.Stock;

@Repository(forEntity = Stock.class) 
public interface StockRepository {

	List<Stock> findAll();

}
