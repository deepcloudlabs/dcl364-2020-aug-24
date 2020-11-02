package com.example;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.example.event.TradeEvent;

public class StudyReactiveProgramming {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Async Programming + Observable Pattern
		SubmissionPublisher<TradeEvent> publisher = 
				new SubmissionPublisher<>();
		Subscriber<TradeEvent> algoTrader = new AlgoTrader();
		Subscriber<TradeEvent> signalProcessor = new SignalProcessor();
		publisher.subscribe(algoTrader);
		publisher.subscribe(signalProcessor);
		List<TradeEvent> events = List.of(new TradeEvent("GARAN", 1000, 20), new TradeEvent("GARAN", 2000, 21),
				new TradeEvent("GARAN", 3000, 22), new TradeEvent("GARAN", 4000, 23), new TradeEvent("GARAN", 1000, 22),
				new TradeEvent("GARAN", 2000, 21), new TradeEvent("GARAN", 2000, 22),
				new TradeEvent("GARAN", 3000, 22));
		Consumer<TradeEvent> submitEvent = publisher::submit;
		Consumer<TradeEvent> sleep1Sec = event -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		};
//		events.forEach(submitEvent.andThen(sleep1Sec));
		events.forEach(submitEvent);
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		publisher.close();
		System.err.println("Done.");
	}
}

class AlgoTrader implements Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent tradeEvent) {
		System.err.println("AlgoTrader: " +Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}		
		System.err.println("AlgoTrader: " + tradeEvent);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println(throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader is done!");
	}

}

class SignalProcessor implements Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent tradeEvent) {
		System.err.println("SignalProcessor: " +Thread.currentThread().getName());
		System.err.println("SignalProcessor: " + tradeEvent);
		this.subscription.request(2);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println(throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("SignalProcessor is done!");
	}

}