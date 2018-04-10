package QL.AST.Expressions;

import QL.AST.ASTNode;
import QL.Analysis.ExpressionVisitorInterface;

public abstract class Expression extends ASTNode {

    Expression(int lineNumber){
        super(lineNumber);
    }

    public abstract <T> T accept(ExpressionVisitorInterface<T> visitor);

    public Boolean isArithmetic(){
        return false;
    }

    public Boolean isLogical(){
        return false;
    }
}