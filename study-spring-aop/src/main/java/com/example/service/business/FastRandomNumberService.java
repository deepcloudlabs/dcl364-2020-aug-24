package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.aop.Audit;
import com.example.aop.Profile;
import com.example.service.RandomNumberService;

@Service
public class FastRandomNumberService implements RandomNumberService {

	@Override
	@Audit
	@Profile
	public int generate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
