package com.example.stockmarket.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

// Value Object: Immutable
public class Symbol {
	private final String value;
	private static final Map<String, Symbol> cache = new ConcurrentHashMap<>();

	private Symbol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Symbol of(String value) {
		Objects.requireNonNull(value);
		// validation
		if (!value.matches("^[a-zA-Z]{3,5}$"))
			throw new IllegalArgumentException("This is not a valid stock symbol!");
		// caching
		var cachedSymbol = cache.get(value);
		if (Objects.isNull(cachedSymbol)) {
			cachedSymbol = new Symbol(value);
			cache.put(value, cachedSymbol);
		}
		return cachedSymbol;
	}

}
