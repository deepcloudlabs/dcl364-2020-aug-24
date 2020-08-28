package com.example.stockmarket.domain;

// Value Object
public class Description {
	private final String value;

	public Description(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Description [value=" + value + "]";
	}

}
