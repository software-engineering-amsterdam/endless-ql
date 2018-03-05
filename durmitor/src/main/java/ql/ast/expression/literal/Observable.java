package ql.ast.expression.literal;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Observer;

public interface Observable {
    
    final List<Observer> observers = new ArrayList<>();
    
    public void notifyObservers();
    public void addObserver(Observer observer);

}
