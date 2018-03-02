package ql.ast.expression.literal;

import ql.ast.expression.Observer;
import ql.ast.expression.Primary;
import ql.evaluator.Operations;
import ql.visitors.interfaces.ValueVisitable;

public abstract class Literal<T>  extends Primary implements ValueVisitable, Operations<Literal<?>>, Observable {

    public abstract T getValue();
    
    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update();
        });
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public Literal<?> evaluate() {
        return this;
    }
    
    public Literal<?> negation() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> negative() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> positive() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> add(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> subtract(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> multiply(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> divide(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> less(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> greater(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> equal(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> notEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> and(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> or(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
}
