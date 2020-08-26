package com.example.service;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class StudyFunctionalInterface {

	public static void main(String[] args) {
		var numbers = List.of(4, 8, 15, 16, 23, 42);
		// classical for-each -> iterator
		var sum = 0;
		for (var number : numbers) { // external loop: iterator pattern
			if (number % 2 == 0) {
				sum += number * number;
			}
		}
		System.out.println("sum= " + sum);
		var totalSum = 0;
		for (var iter = numbers.iterator(); iter.hasNext();) {
			var number = iter.next();
			if (number % 2 == 0) {
				totalSum += number * number;
			}
		}
		System.out.println("totalSum= " + totalSum);
		// functional programming: Functional Interface + Stream API ->
		// Filter/Map/Reduce + Lambda Expression
		// internal loop: spliterator
		// Parallel Programming Pattern: FMR, Hadoop (Big Data Platform) i) HDFS ii)
		// MapReduce
		// variable -> Primitive/Class/function?
		// function? -> high-order function -> @FunctionalInterface -> SAM -> Built-in
		// Functional Interface
		// Lambda Expression: Pure Function? Side Effect
		int one = 1; // effectively final
		Predicate<Integer> ifOdd = n -> n % 2 == one; // (2) Lambda Expression: Function Object
		Function<Integer, Integer> squared = n -> n * n;
		BinaryOperator<Integer> sumReducer = (s, n) -> s + n;
		var total = numbers.stream() // 4 8 15 16 23 42
				.filter(ifOdd.negate()) // 15 23
				// .filter(Fun::odd) // (3) Method Reference
				// .filter(n -> Fun.odd(n)) // Fun::odd
				.map(squared) // 225 529
				.reduce(0, sumReducer); // 0 -> 0+225: 225 -> 225+529: 754
		System.out.println(total);
	}

}

interface Fun {
	public static boolean odd(int n) { // Functional Programming Utility Function
		return n % 2 == 1;
	}
}