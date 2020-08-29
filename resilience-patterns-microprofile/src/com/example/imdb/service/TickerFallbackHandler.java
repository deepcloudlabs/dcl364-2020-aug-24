package com.example.imdb.service;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import com.example.imdb.dto.Ticker;

public class TickerFallbackHandler implements FallbackHandler<Ticker> {
	@Override
	public Ticker handle(ExecutionContext context) {
		return new Ticker("btcusdt", "10_000.0");
	}
}
