package ql.ast.expression;

public abstract class Operation {

    public abstract boolean equals(Operation query) ;
    
    public boolean equals(UnaryOperation operation) {
        return false;
    }
    
    public boolean equals(BinaryOperation operation) {
        return false;
    }
}
