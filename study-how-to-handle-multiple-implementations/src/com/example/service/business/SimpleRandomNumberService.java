package com.example.service.business;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.service.RandomNumberService;

//@Stateless
//@Standard
@Named
@Singleton
//@Alternative
public class SimpleRandomNumberService implements RandomNumberService {
	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("SimpleRandomNumberService::generate");
		return random.nextInt(max - min + 1) + min;
	}

}
