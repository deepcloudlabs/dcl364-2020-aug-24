package com.example;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BlockingExample {

	public static void main(String[] args) throws InterruptedException {
		var numbers = Collections.synchronizedList(new ArrayList<List<Integer>>());
		Function<List<Integer>,List<Integer>> fn = 
		lotteryNumbers -> {
			System.err.println(Thread.currentThread().getName()+": "+lotteryNumbers); 
			return lotteryNumbers;
		};
		for (var i=0;i<10;++i)
			 draw().thenApplyAsync(fn);// 1sec
		//numbers.forEach(System.out::println);
		TimeUnit.SECONDS.sleep(100);
	}

	public static CompletableFuture<List<Integer>> draw(){
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1); // IO (Network/Disk IO)
			} catch (InterruptedException e) { }
			return ThreadLocalRandom.current().ints(1, 50).distinct().limit(6).sorted().boxed().collect(toList());
		});
		
	}
}
