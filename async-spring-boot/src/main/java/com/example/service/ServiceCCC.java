package com.example.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.event.Trade;

@Service
public class ServiceCCC {
	@EventListener
	public void listenTrade(Trade trade) {
		System.out.println("CCC is listening: " + trade);
	}
}
