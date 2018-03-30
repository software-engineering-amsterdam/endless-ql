package QL.AST.Expressions;

import QL.Analysis.EvaluationType;
import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.ASTNode;

public abstract class Expression extends ASTNode {

    Expression(int lineNumber){
        super(lineNumber);
    }

    //public abstract EvaluationType returnType();
    //public abstract Constant evaluate(); //should this also be done via visitor?

    public abstract <T> T accept(ExpressionVisitorInterface<T> visitor);

    public Boolean isArithmetic(){
        return false;
    }

    public Boolean isLogical(){
        return false;
    }

    /*
    @Override
    public String toString(){
       return this.evaluate().toString();
    }*/
}