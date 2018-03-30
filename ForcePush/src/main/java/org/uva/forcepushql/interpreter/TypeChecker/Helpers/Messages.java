package org.uva.forcepushql.interpreter.TypeChecker.Helpers;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

public class Messages
{

    private final Collection<Entry<MessageTypes, String>> messagesCollection = new ArrayList<>();

    public enum MessageTypes {
        WARNING,
        UNKNOWN,
        ERROR
    }

    public void addMessage(String message, MessageTypes messageType) {
        this.messagesCollection.add(new SimpleEntry<>(messageType, message));
    }

    public void addMessages(Messages messages){
        this.messagesCollection.addAll(messages.messagesCollection);
    }

    public boolean hasMessagePresent(MessageTypes messageTypes){
        for (Entry<MessageTypes, String> entry : this.messagesCollection){
            if(entry.getKey() == messageTypes){
                return true;
            }
        }
        return false;
    }

    private Collection<Entry<MessageTypes, String>> getMessagesCollection(){
        return this.messagesCollection;
    }

    @Override
    public String toString() {
        return this.messagesCollection.toString();
    }

    public Iterable<String> getMessage(MessageTypes messageTypes) {
        Collection<String> messages = new ArrayList<>();
        for (Entry<MessageTypes, String> entry : this.messagesCollection) {
            if (entry.getKey() == messageTypes)
                messages.add(entry.getValue());
        }

        return messages;
    }

}
