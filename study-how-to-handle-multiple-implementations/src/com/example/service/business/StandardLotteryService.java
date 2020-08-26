package com.example.service.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

@Singleton
public class StandardLotteryService implements LotteryService {
	@Inject
	@Any
	// @Standard (1)
	private Instance<RandomNumberService> instances;
	private List<RandomNumberService> randomNumberServices;
	private AtomicInteger counter= new AtomicInteger(0);
	
	@PostConstruct
	public void init() {
		// selection strategy among multiple implementations
		// Spring Boot @ConditionalOnXYZ
		randomNumberServices = new ArrayList<>();
		instances.forEach(randomNumberServices::add);
	}
	
	@Override
	public List<Integer> draw(int max, int size) {
		var index = counter.getAndIncrement() % randomNumberServices.size();
		var randomNumberService = randomNumberServices.get(index);
		return IntStream.generate(() -> randomNumberService.generate(1, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column).mapToObj(i -> this.draw(max, size)).collect(Collectors.toList());
	}

}
