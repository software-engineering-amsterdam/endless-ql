package ql.visitor;

import ql.model.Form;
import ql.model.expression.Expression;
import ql.model.expression.Identifier;
import ql.model.expression.binary.*;
import ql.model.expression.constant.*;
import ql.model.expression.unary.NegationExpression;
import ql.model.expression.unary.NotExpression;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Question;
import ql.model.statement.Statement;

public interface IQLVisitor<T> {
    T visit(Form form);

    // Statements
    T visit(Statement statement);

    T visit(IfBlock ifBlock);

    T visit(IfElseBlock ifElseBlock);

    T visit(Question question);

    // Expressions
    T visit(Expression expression);

    T visit(DivisionExpression expression);

    T visit(MultiplicationExpression expression);

    T visit(SubtractionExpression expression);

    T visit(SumExpression expression);

    T visit(EqualExpression expression);

    T visit(GreaterEqualExpression expression);

    T visit(GreaterThanExpression expression);

    T visit(LessEqualExpression expression);

    T visit(LessThanExpression expression);

    T visit(AndExpression expression);

    T visit(OrExpression expression);

    T visit(NotExpression expression);

    T visit(NegationExpression expression);

    T visit(BooleanConstant expression);

    T visit(DateConstant expression);

    T visit(IntegerConstant constant);

    T visit(DecimalConstant constant);

    T visit(MoneyConstant constant);

    T visit(StringConstant constant);

    T visit(UndefinedConstant constant);

    T visit(Identifier identifier);
}