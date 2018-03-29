package QL.AST.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;

public class UndefinedConstant extends Constant<Object> {
    private EvaluationType type;

    public UndefinedConstant(int line){
        super(null, line);
        setType(EvaluationType.Undefined);
    }

    public UndefinedConstant(EvaluationType type, int line){
        super(null, line);
        setType(type);
    }

    public EvaluationType returnType() { return type; }

    public void setType(EvaluationType type) { this.type = type; }

    @Override
    public String toString(){
        return "Undefined";
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
