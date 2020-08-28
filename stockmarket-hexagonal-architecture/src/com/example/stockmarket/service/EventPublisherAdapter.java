package com.example.stockmarket.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.example.stockmarket.event.BaseEvent;
import com.example.stockmarket.infra.EventPublisher;

@Stateless
public class EventPublisherAdapter implements EventPublisher {
	@Inject private Event<BaseEvent> event;
	
	@Override
	public void publishEvent(BaseEvent be) {
		event.fire(be);
	}

}
