package ql.ast.expression;


public abstract class Operator extends Expression {
    
    protected final Operations legalOperations;
    
    public Operator() {
        legalOperations = new Operations();
        initOperations();
    }
    
    public boolean isUnary() {
        return false;
    }
    
    public boolean isBinary() {
        return false;
    }
    
    protected abstract void initOperations();
    public abstract String getOperator();
}
