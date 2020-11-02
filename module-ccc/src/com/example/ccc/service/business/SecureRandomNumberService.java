package com.example.ccc.service.business;

import java.security.SecureRandom;
import java.util.Random;

import com.example.ccc.service.Quality;
import com.example.ccc.service.QualityLevel;
import com.example.ccc.service.RandomNumberService;

@Quality(QualityLevel.FAST)
public class SecureRandomNumberService implements RandomNumberService {
	private Random rand = new SecureRandom();

	public SecureRandomNumberService() {
		System.err.println("SecureRandomNumberService is created!");
	}

	@Override
	public int generate(int min, int max) {
		System.err.println("FAST Implementation");
		return rand.nextInt(max - min) + min;
	}

}
