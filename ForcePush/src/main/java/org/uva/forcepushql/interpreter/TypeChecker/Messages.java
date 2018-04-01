package org.uva.forcepushql.interpreter.TypeChecker;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

public class Messages
{

    private final Collection<Entry<MessageTypes, String>> messagesCollection = new ArrayList<>();

    public boolean isEmpty(){
        return messagesCollection.isEmpty();
    }

    public void addMessage(String message, MessageTypes messageType)
    {
        this.messagesCollection.add(new SimpleEntry<>(messageType, message));
    }

    public boolean allWarning(){
        boolean allWarning = true;
        for (Entry<MessageTypes, String> s: messagesCollection) {
            allWarning = allWarning && s.getKey().equals(MessageTypes.WARNING);
        }

        return allWarning;
    }


    @Override
    public String toString()
    {
        return this.messagesCollection.toString();
    }


    public enum MessageTypes
    {
        WARNING,
        ERROR
    }

}
