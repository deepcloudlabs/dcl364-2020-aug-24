package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.event.Trade;

@Service
public class ServiceAAA {
	@Autowired
	private ApplicationEventPublisher publisher;
	private int i=1000;
	@Scheduled(fixedRate = 1_000)
	public void fun() {
		Trade trade = new Trade("GARAN",i++,20);
		publisher.publishEvent(trade);
	}
}
