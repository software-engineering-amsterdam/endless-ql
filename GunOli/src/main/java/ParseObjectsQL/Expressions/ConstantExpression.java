package ParseObjectsQL.Expressions;

import Visitor.ExpressionTable;
import ParseObjectsQL.Expressions.ExpressionConstants.Constant;

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


    //Todo: Need to find a better way to build the constant expressions with the QuestionMap
    @Override
    public EvaluationType returnType(){
        return expressionTable.getExpression(this.identifier).returnType();
    }

    @Override
    public Constant evaluate(){
        return expressionTable.getExpression(this.identifier).evaluate();
    }

    // Overloaded methods for when parsing is complete and Question Map is populated
   /* public EvaluationType returnType(QuestionMap questionMap){
        return questionMap.getQuestion(this.identifier).returnType();
    }

    public Constant evaluate(QuestionMap questionMap){
        return questionMap.getQuestion(this.identifier).evaluate();
    }*/
}
