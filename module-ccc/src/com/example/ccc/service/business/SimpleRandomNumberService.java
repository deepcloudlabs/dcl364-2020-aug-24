package com.example.ccc.service.business;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.example.ccc.service.Quality;
import com.example.ccc.service.QualityLevel;
import com.example.ccc.service.RandomNumberService;

@Quality(QualityLevel.CHEAP)
public class SimpleRandomNumberService implements RandomNumberService {
	private Random rand = ThreadLocalRandom.current();

	public SimpleRandomNumberService() {
		System.err.println("SimpleRandomNumberService is created!");
	}

	@Override
	public int generate(int min, int max) {
		System.err.println("CHEAP Implementation");
		return rand.nextInt(max - min + 1) + min;
	}

}
