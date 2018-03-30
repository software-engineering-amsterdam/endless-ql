package QL.AST.Expressions;

import QL.Analysis.EvaluationType;
import QL.Analysis.ExpressionVisitorInterface;
import QL.QLVisitor.ExpressionTable;

public class IdentifierExpression extends Expression {
    private String identifier;
    private ExpressionTable expressionTable;

    public IdentifierExpression(String id, ExpressionTable expressionTable, int line){
        super(line);
        setIdentifier(id);
        this.expressionTable = expressionTable;
    }

    public String getIdentifier(){ return identifier; }

    public void setIdentifier(String id){
        this.identifier = id;
    }

    /*@Override
    public EvaluationType returnType(){
        return expressionTable.getExpression(this.identifier).returnType();
    }*/

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
    /*
    @Override
    public Constant evaluate(){
        return expressionTable.getExpression(this.identifier).evaluate();
    }*/
}
