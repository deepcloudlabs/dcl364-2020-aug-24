package com.example.stockmarket.event;

import java.util.UUID;

public class BaseEvent {
	private final String eventId;
	private final String description;
	private final Object data;

	public BaseEvent(String description, Object data) {
		this(UUID.randomUUID().toString(), description, data);
	}

	public BaseEvent(String eventId, String description, Object data) {
		this.eventId = eventId;
		this.description = description;
		this.data = data;
	}

	public String getEventId() {
		return eventId;
	}

	public String getDescription() {
		return description;
	}

	public Object getData() {
		return data;
	}

}
