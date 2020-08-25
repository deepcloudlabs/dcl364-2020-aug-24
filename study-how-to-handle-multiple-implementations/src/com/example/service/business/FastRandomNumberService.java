package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.service.RandomNumberService;

// (1) Qualify implementations using @Qualifier annotations
//@Stateless
//@Fast

// (2) CDI Solution
@Named
@Singleton
//@Default // default implementation
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
