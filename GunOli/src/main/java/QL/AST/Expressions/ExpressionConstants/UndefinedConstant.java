package QL.AST.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;

public class UndefinedConstant extends Constant<Object> {
    private EvaluationType type;

    public UndefinedConstant(int line){
        super(null, line);
        this.type = EvaluationType.Undefined;
    }

    public UndefinedConstant(EvaluationType type, int line){
        super(null, line);
        this.type = type;
    }

    @Override
    public String toString(){
        return "Undefined";
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
