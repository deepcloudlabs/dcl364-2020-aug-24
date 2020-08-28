package com.example.stockmarket.dto;

public class UnregisterCompanyRequest {
	private String url;

	public UnregisterCompanyRequest() {
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UnregisterCompanyRequest [url=" + url + "]";
	}

}
