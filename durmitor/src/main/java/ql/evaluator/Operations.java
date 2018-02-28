package ql.evaluator;

public interface Operations<T> {

    T negation();
    T negative();
    T positive();
    
    T and(T secondOperand);
    T or(T secondOperand);

    T add(T secondOperand);
    T subtract(T secondOperand);
    T multiply(T secondOperand);
    T divide(T secondOperand);
    
    T less(T secondOperand);
    T lessEqual(T secondOperand);
    T greater(T secondOperand);
    T greaterEqual(T secondOperand);
    T equal(T secondOperand);
    T notEqual(T secondOperand);
}
