package com.example.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public ExecutorService executorService(@Value("${pool.size}") int poolSize) {
//		return Executors.newFixedThreadPool(poolSize);
//		return Executors.newSingleThreadExecutor();
		SynchronousQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();
		return new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, workQueue);
	}
}
