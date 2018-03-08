package ql.helpers;

import java.util.HashSet;
import java.util.Set;

public interface Observable {
    
    final Set<Observer> observers = new HashSet<Observer>();
    
    public void notifyObservers();
    public void addObserver(Observer observer);

}
