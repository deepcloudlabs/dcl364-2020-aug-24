package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class StudyFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// fun().thenAccept(System.out::println);
		CompletableFuture<Integer> result = fun();
		while (!result.isDone()) {
			System.err.println("Working hard!!!");
		}
		System.err.println(result.get()); // get(): blocking
		System.err.println("Done!");
	}
	// async
	public static CompletableFuture<Integer> fun() { 
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(e.getSuppressed());
			}
			return 42;
	    });
	}
}
