package QL.ParseObjectsQL.Expressions;

import QL.QLVisitor.ExpressionTable;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;

public class ConstantExpression extends Expression {
    private String identifier;
    private ExpressionTable expressionTable;

    public ConstantExpression(String id, ExpressionTable expressionTable){
        setIdentifier(id);
        this.expressionTable = expressionTable;
    }

    public void setIdentifier(String id){
        this.identifier = id;
    }

    @Override
    public EvaluationType returnType(){
        return expressionTable.getExpression(this.identifier).returnType();
    }

    @Override
    public Constant evaluate(){
        return expressionTable.getExpression(this.identifier).evaluate();
    }
}
