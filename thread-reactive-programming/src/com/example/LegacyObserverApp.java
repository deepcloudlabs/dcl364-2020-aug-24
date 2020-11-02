package com.example;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings("deprecation")
public class LegacyObserverApp {

	public static void main(String[] args) {
		Observable observable = new TradeEventObservable();
		Observer observer1 = new Observer() {

			@Override
			public void update(Observable o, Object te) {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
				}
				System.err.println(te);
			}
		};
		Observer observer2 = new Observer() { // anonymous class
			private String volume = "0.0";

			@Override
			public void update(Observable o, Object event) {
				TradeEvent te = (TradeEvent) event;
				volume += te.getPrice() * te.getQuantity();
				System.err.println("Volume: " + volume);
			}
		};
		System.err.println(observer1.getClass().getName());
		System.err.println(observer2.getClass().getName());
		observable.addObserver(observer1);
		observable.addObserver(observer2);
		List<TradeEvent> events = List.of(new TradeEvent("GARAN", 1000, 20), new TradeEvent("GARAN", 2000, 21),
				new TradeEvent("GARAN", 3000, 22), new TradeEvent("GARAN", 4000, 23), new TradeEvent("GARAN", 1000, 22),
				new TradeEvent("GARAN", 2000, 21), new TradeEvent("GARAN", 2000, 22),
				new TradeEvent("GARAN", 3000, 22));
		events.forEach(observable::notifyObservers);
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}

}
