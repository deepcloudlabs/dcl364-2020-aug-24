package com.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	@Autowired
	private ExecutorService threadPool;

	@Async
	public Future<Integer> fun() {
		return threadPool.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				System.out.println(e.getSuppressed());
			}
			return 42;
		});
	}
}
