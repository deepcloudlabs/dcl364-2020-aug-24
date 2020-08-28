package com.example.stockmarket.dto;

public class RegisterStockResponse {
	private String status;
	private String symbol;

	public RegisterStockResponse(String status, String symbol) {
		this.status = status;
		this.symbol = symbol;
	}

	public String getStatus() {
		return status;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "RegisterStockResponse [status=" + status + ", symbol=" + symbol + "]";
	}

}
