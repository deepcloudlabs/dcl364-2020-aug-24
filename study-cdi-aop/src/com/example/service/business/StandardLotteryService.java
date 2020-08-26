package com.example.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.example.aop.Audit;
import com.example.aop.Cache;
import com.example.aop.Profile;
import com.example.service.LotteryService;
import com.example.service.RandomNumberService;

@Singleton
@Audit
@Profile
public class StandardLotteryService implements LotteryService {
	@Inject
    private RandomNumberService randomNumberService;
	
	@Override
	@Cache
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(() -> randomNumberService.generate(1, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column).mapToObj(i -> this.draw(max, size)).collect(Collectors.toList());
	}

}
