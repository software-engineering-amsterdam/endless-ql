package ParseObjects.Expressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.ExpressionConstants.UndefinedConstant;
import ParseObjects.QuestionMap;

public class ConstantExpression extends Expression {
    private String identifier;

    public ConstantExpression(String id){
        setIdentifier(id);
    }

    public void setIdentifier(String id){
        this.identifier = id;
    }


    //Todo: Need to find a better way to build the constant expressions with the QuestionMap
    @Override
    public EvaluationType returnType(){
        return EvaluationType.Undefined;
    }

    @Override
    public Constant evaluate(){
        return new UndefinedConstant();
    }

    // Overloaded methods for when parsing is complete and Question Map is populated
    public EvaluationType returnType(QuestionMap questionMap){
        return questionMap.getQuestion(this.identifier).returnType();
    }

    public Constant evaluate(QuestionMap questionMap){
        return questionMap.getQuestion(this.identifier).evaluate();
    }
}
