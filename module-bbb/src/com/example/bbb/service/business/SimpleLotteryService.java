package com.example.bbb.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.bbb.service.LotteryService;
import com.example.ccc.service.RandomNumberService;

public class SimpleLotteryService implements LotteryService {
	private RandomNumberService randomNumberService;
	
	public void setRandomNumberService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@Override
	public List<Integer> draw() {
		return IntStream.generate( () -> randomNumberService.generate(1, 50))
				     .distinct()
				     .limit(6)
				     .sorted()
				     .boxed()
				     .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int n) {
		return IntStream.range(0, n)
				  .mapToObj( i -> this.draw())
				  .collect(Collectors.toList());
	}

}
