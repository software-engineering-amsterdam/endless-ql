package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

public class Messages {

    private final Collection<Entry<MessageTypes, String>> messages = new ArrayList<>();

    public final void addMessages(final Messages messages) {
        this.messages.addAll(messages.messages);
    }

    public final void addMessage(final String message, final MessageTypes messageType) {
        this.messages.add(new SimpleEntry<>(messageType, message));
    }

    public final boolean hasMessagePresent(final MessageTypes messageTypes) {
        for (final Entry<MessageTypes, String> entry : this.messages) {
            if (entry.getKey() == messageTypes)
                return true;
        }

        return false;
    }

    private Collection<Entry<MessageTypes, String>> getMessages() {
        return this.messages;
    }

    @Override
    public final String toString() {
        return this.messages.toString();
    }

    public final Iterable<String> getMessage(final MessageTypes messageTypes) {
        final Collection<String> messages = new ArrayList<>();
        for (final Entry<MessageTypes, String> entry : this.messages) {
            if (entry.getKey() == messageTypes)
                messages.add(entry.getValue());
        }

        return messages;
    }
}