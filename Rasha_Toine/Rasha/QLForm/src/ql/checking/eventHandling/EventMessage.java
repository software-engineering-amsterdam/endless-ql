package ql.checking.eventHandling;

import ql.utils.MessageTypeEnum;

public class EventMessage {

	private String message;
	private MessageTypeEnum type;
	
	public EventMessage(String message, MessageTypeEnum type) {
		this.message = message;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MessageTypeEnum getType() {
		return type;
	}
	public void setType(MessageTypeEnum type) {
		this.type = type;
	}

}