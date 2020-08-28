package com.example.stockmarket.infra;

import com.example.stockmarket.event.BaseEvent;

public interface EventPublisher {
	void publishEvent(BaseEvent event);
}
