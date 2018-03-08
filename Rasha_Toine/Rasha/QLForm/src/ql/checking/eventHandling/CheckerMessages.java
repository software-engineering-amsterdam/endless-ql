package ql.checking.eventHandling;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import ql.utils.MessageTypeEnum;

public class CheckerMessages {

	private  Map<MessageTypeEnum, List> messages = new HashMap<>();
	
	public CheckerMessages() {
		
	}

	public Map<MessageTypeEnum, List> getMessages() {
		return messages;
	}

	public void setMessages(Map<MessageTypeEnum, List> messages) {
		this.messages = messages;
	}
}
