package org.uva.sea.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.ql.interpreter.dataObject.MessageTypes;

import java.util.ArrayList;
import java.util.List;

public class Messages {
    private List<String> messages = new ArrayList<>();

    private MessageTypes messageTypes;

    public Messages(MessageTypes messageTypes) {
        this.messageTypes = messageTypes;
    }

    public void addMessageList(List<String> messages) {
        this.messages.addAll(messages);
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public boolean hasMessagePresent() {
        return !this.messages.isEmpty();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void clear() {
        this.messages.clear();
    }

    public MessageTypes getMessageTypes() {
        return messageTypes;
    }

    @Override
    public String toString() {
        return messages.toString();
    }
}