package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Messages {

    private List<Map.Entry<MessageTypes, String>> messages = new ArrayList<>();

    public void addMessageList(Messages messages) {
        this.messages.addAll(messages.getMessages());
    }

    public void addMessage(String message, MessageTypes messageType) {
        this.messages.add(new AbstractMap.SimpleEntry<>(messageType, message));
    }

    public boolean hasMessagePresent(MessageTypes messageTypes) {
        for(Map.Entry<MessageTypes, String> entry : this.messages) {
            if(entry.getKey() == messageTypes)
                return true;
        }

        return false;
    }

    public List<Map.Entry<MessageTypes, String>> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return messages.toString();
    }

    public List<String> getMessage(MessageTypes messageTypes) {
        List<String> messages = new ArrayList<>();
        for(Map.Entry<MessageTypes, String> entry : this.messages) {
            if(entry.getKey() == messageTypes)
                messages.add(entry.getValue());
        }

        return messages;
    }
}