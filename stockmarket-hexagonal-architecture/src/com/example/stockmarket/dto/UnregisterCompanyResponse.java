package com.example.stockmarket.dto;

import java.util.List;

public class UnregisterCompanyResponse {
	private String status;
	private String url;
	private List<String> symbols;

	public UnregisterCompanyResponse(String status, String url, List<String> symbols) {
		this.status = status;
		this.url = url;
		this.symbols = symbols;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

	@Override
	public String toString() {
		return "UnregisterCompanyResponse [status=" + status + ", url=" + url + ", symbols=" + symbols + "]";
	}

}
