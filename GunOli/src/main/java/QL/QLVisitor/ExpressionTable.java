package QL.QLVisitor;

import QL.AST.Expressions.Expression;
import java.util.HashMap;
import java.util.Map;

public class ExpressionTable {
    private Map<String, Expression> expressionTable;

    public ExpressionTable(){
        this.expressionTable = new HashMap<>();
    }

    public void addExpression(String id, Expression expr){
        this.expressionTable.put(id, expr);
    }

    public Expression getExpression(String id){
        return expressionTable.get(id);
    }

    public void updateExpression(String id, Expression expr){
        this.expressionTable.replace(id, expr);
    }
    // for debugging
    public Boolean exisitsIn(String id){
        return this.expressionTable.containsKey(id);
    }
}
