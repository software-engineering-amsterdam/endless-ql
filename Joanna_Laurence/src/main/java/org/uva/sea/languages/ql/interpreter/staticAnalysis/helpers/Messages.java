package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

public class Messages {

    private final Collection<Entry<MessageTypes, String>> messages = new ArrayList<>();

    public void addMessages(Messages messages) {
        this.messages.addAll(messages.getMessages());
    }

    public void addMessage(String message, MessageTypes messageType) {
        this.messages.add(new SimpleEntry<>(messageType, message));
    }

    public boolean hasMessagePresent(MessageTypes messageTypes) {
        for (Entry<MessageTypes, String> entry : this.messages) {
            if (entry.getKey() == messageTypes)
                return true;
        }

        return false;
    }

    public Collection<Entry<MessageTypes, String>> getMessages() {
        return this.messages;
    }

    @Override
    public String toString() {
        return this.messages.toString();
    }

    public Iterable<String> getMessage(MessageTypes messageTypes) {
        Collection<String> messages = new ArrayList<>();
        for (Entry<MessageTypes, String> entry : this.messages) {
            if (entry.getKey() == messageTypes)
                messages.add(entry.getValue());
        }

        return messages;
    }
}