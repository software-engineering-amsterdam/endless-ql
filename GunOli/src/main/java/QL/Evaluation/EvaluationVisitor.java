package QL.Evaluation;

import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.BinaryExpressions.AdditionExpression;
import QL.AST.Expressions.BinaryExpressions.AndExpression;
import QL.AST.Expressions.BinaryExpressions.DivisionExpression;
import QL.AST.Expressions.BinaryExpressions.EqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterThanExpression;
import QL.AST.Expressions.BinaryExpressions.LessOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.LessThanExpression;
import QL.AST.Expressions.BinaryExpressions.MultiplicationExpression;
import QL.AST.Expressions.BinaryExpressions.NotEqualExpression;
import QL.AST.Expressions.BinaryExpressions.OrExpression;
import QL.AST.Expressions.BinaryExpressions.SubtractExpression;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Expressions.ExpressionConstants.DateConstant;
import QL.AST.Expressions.ExpressionConstants.DecimalConstant;
import QL.AST.Expressions.ExpressionConstants.IntegerConstant;
import QL.AST.Expressions.ExpressionConstants.MoneyConstant;
import QL.AST.Expressions.ExpressionConstants.StringConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.UnaryExpression;
import QL.AST.Expressions.UnaryExpressions.NegationExpression;
import QL.AST.Expressions.UnaryExpressions.NotExpression;
import QL.AST.Question;
import QL.Visitors.ExpressionVisitorInterface;
import QL.Evaluation.Evaulators.EvaluatorFactory;
import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.DateValue;
import QL.Evaluation.Values.NumericValue;
import QL.Evaluation.Values.StringValue;
import QL.Evaluation.Values.UndefinedValue;

public class EvaluationVisitor implements ExpressionVisitorInterface<Value> {
    private EvaluatorFactory evaluatorFactory;
    private ExpressionTable expressionTable;

    public EvaluationVisitor(ExpressionTable expressionTable){
        this.evaluatorFactory = new EvaluatorFactory();
        this.expressionTable = expressionTable;
    }

    public String evaluateQuestion(Question question){
        Value questionAnswer = expressionTable.getExpression(question.getIdentifier()).accept(this);
        return questionAnswer.getValue() + "";
    }

    private Value evaluateBinaryExpression(String evalType, BinaryExpression expression){
        Value left = expression.getExprLeft().accept(this);
        Value right = expression.getExprRight().accept(this);
        Evaluator evaluator = evaluatorFactory.createBinaryEvaluator(evalType, left, right);

        return evaluator.evaluate();
    }

    private Value evaluateUnaryExpression(String evaluationType, UnaryExpression expression){
        Value value = expression.getExpression().accept(this);
        Evaluator evaluator = evaluatorFactory.createUnaryEvaluator(evaluationType, value);

        return evaluator.evaluate();
    }

    @Override
    public Value visit(Expression expression) { return expression.accept(this); }

    @Override
    public Value visit(AdditionExpression expression) { return evaluateBinaryExpression("ADD", expression); }

    @Override
    public Value visit(AndExpression expression) { return evaluateBinaryExpression("AND", expression); }

    @Override
    public Value visit(DivisionExpression expression) { return evaluateBinaryExpression("DIV", expression); }

    @Override
    public Value visit(EqualExpression expression) { return evaluateBinaryExpression("EQ", expression); }

    @Override
    public Value visit(GreaterOrEqualExpression expression) { return evaluateBinaryExpression("GE", expression); }

    @Override
    public Value visit(GreaterThanExpression expression) { return evaluateBinaryExpression("GT", expression); }

    @Override
    public Value visit(LessOrEqualExpression expression) { return evaluateBinaryExpression("LE", expression); }

    @Override
    public Value visit(LessThanExpression expression) { return evaluateBinaryExpression("LT", expression); }

    @Override
    public Value visit(MultiplicationExpression expression) { return evaluateBinaryExpression("MUL", expression); }

    @Override
    public Value visit(NotEqualExpression expression) { return evaluateBinaryExpression("NEQ", expression); }

    @Override
    public Value visit(OrExpression expression) { return evaluateBinaryExpression("OR", expression); }

    @Override
    public Value visit(SubtractExpression expression) { return evaluateBinaryExpression("SUB", expression); }

    @Override
    public Value visit(NegationExpression expression) {
        return evaluateUnaryExpression("NEG", expression);
    }

    @Override
    public Value visit(NotExpression expression) {
        return evaluateUnaryExpression("NOT", expression);
    }

    @Override
    public Value visit(IdentifierExpression expression) {
        return this.expressionTable.getExpression(expression.getIdentifier()).accept(this);
    }

    @Override
    public Value visit(BooleanConstant expression) {
        return new BooleanValue(expression.getValue());
    }

    @Override
    public Value visit(DateConstant expression) {
        return new DateValue(expression.getValue());
    }

    @Override
    public Value visit(DecimalConstant expression) {
        return new NumericValue(expression.getValue());
    }

    @Override
    public Value visit(IntegerConstant expression) {
        return new NumericValue(expression.getValue());
    }

    @Override
    public Value visit(MoneyConstant expression) {
        return new NumericValue(expression.getValue());
    }

    @Override
    public Value visit(StringConstant expression) {
        return new StringValue(expression.getValue());
    }

    @Override
    public Value visit(UndefinedConstant expression) {
        return new UndefinedValue();
    }
}
