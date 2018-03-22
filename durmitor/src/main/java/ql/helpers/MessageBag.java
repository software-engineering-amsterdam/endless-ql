package ql.helpers;

import java.util.ArrayList;

import ql.exceptions.QLException;

@SuppressWarnings("serial")
public class MessageBag extends ArrayList<QLException> {

    public boolean hasErrors() {
        return !this.isEmpty();
    }
    
    public void print() {
        for(QLException e : this) System.out.println(e);
    }
}
