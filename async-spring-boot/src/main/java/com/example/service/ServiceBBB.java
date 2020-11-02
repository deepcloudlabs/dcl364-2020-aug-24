package com.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.event.Trade;

@Service
public class ServiceBBB {
	@Autowired
	private ExecutorService threadPool;

	@EventListener
	public void listen(Trade trade) {
		fun(trade);
	}

	public void fun(final Trade trade) {
		threadPool.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				System.out.println(e.getSuppressed());
			}
			System.out.println("BBB is listening: " + trade);
		});
	}
}
