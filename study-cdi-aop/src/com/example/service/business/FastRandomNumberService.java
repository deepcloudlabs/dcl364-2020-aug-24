package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.aop.Audit;
import com.example.aop.Profile;
import com.example.service.RandomNumberService;

@Named
@Singleton
public class FastRandomNumberService implements RandomNumberService {

	@Override
	@Audit
	@Profile
	public int generate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
