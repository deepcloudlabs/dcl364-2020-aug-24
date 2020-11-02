package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.service.BusinessService;

@SpringBootApplication
@EnableScheduling
public class AsyncSpringBootApplication implements ApplicationRunner {

	@SuppressWarnings("unused")
	@Autowired
	private BusinessService businessService;

	public static void main(String[] args) {
		SpringApplication.run(AsyncSpringBootApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		Future<Integer> result = businessService.fun();
//		while(!result.isDone()) {
//			System.out.println("Working hard!");
//		}
//		System.err.println(result.get());
	}

}
