package ql.typechecker;

import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.GroupExpression;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.BooleanLiteral;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.statements.*;
import ql.typechecker.messages.Messages;
import ql.typechecker.messages.error.DuplicateDeclaration;
import ql.typechecker.messages.error.InvalidOperandsTypeToOperator;
import ql.typechecker.messages.error.TypeMismatch;
import ql.typechecker.messages.error.UndefinedReference;
import ql.typechecker.messages.warning.DuplicateLabel;
import ql.types.*;
import ql.types.Boolean;
import ql.types.Integer;
import ql.types.String;
import ql.visitors.ExpressionVisitor;
import ql.visitors.FormVisitor;
import ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker implements FormVisitor, StatementVisitor<Void>, ExpressionVisitor<Type> {
    private final Messages messages;
    private final List<java.lang.String> questionLabels;
    private final DeclarationTable declarations;

    public TypeChecker(Messages messages) {
        this.messages = messages;
        this.questionLabels = new ArrayList<>();
        this.declarations = new DeclarationTable();
    }

    @Override
    public void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
    }

    @Override
    public Void visit(CalculableQuestion calculableQuestion) {
        checkRedeclaration(calculableQuestion);
        checkLabelDuplication(calculableQuestion);

        calculableQuestion.getCalculableValue().accept(this);
        return null;
    }

    @Override
    public Void visit(IfThen ifThen) {
        Expression expression = ifThen.getCondition();
        validateCondition(expression, ifThen.getLineNumber());

        ifThen.getCondition().accept(this);

        for (Statement statement : ifThen.getThenStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse) {
        Expression expression = ifThenElse.getCondition();
        validateCondition(expression, ifThenElse.getLineNumber());

        ifThenElse.getCondition().accept(this);

        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this);
        }
        for (Statement statement : ifThenElse.getElseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    private void validateCondition(Expression expression, int line) {
        Type type = expression.accept(this);

        if (!type.isBoolean()) {
            messages.addError(new TypeMismatch(line, type.toString()));
        }
    }

    @Override
    public Void visit(Question question) {
        checkRedeclaration(question);
        checkLabelDuplication(question);
        return null;
    }

    private void checkRedeclaration(Question question) {
        if (declarations.exists(question.getIdentifier())) {
            messages.addError(new DuplicateDeclaration(question.getLineNumber(), question.getIdentifier().toString()));
        }
        declarations.add(question.getIdentifier(), question.getType());
    }

    private void checkLabelDuplication(Question question) {
        if (questionLabels.contains(question.getDescription().getValue())) {
            messages.addWarning(new DuplicateLabel(question.getLineNumber(), question.getDescription().getValue()));
        }
        questionLabels.add(question.getDescription().getValue());
    }

    @Override
    public Type visit(Addition addition) {
        return checkBinaryExpression(addition, "Addition");
    }

    @Override
    public Type visit(Division division) {
        return checkBinaryExpression(division, "Division");
    }

    @Override
    public Type visit(Equal equal) {
        checkBinaryExpression(equal, "Equal");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(GreaterThan greaterThan) {
        checkBinaryExpression(greaterThan, "GreaterThan");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(GreaterThanOrEqual greaterThanOrEqual) {
        checkBinaryExpression(greaterThanOrEqual, "GreaterThanOrEqual");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(LessThan lessThan) {
        checkBinaryExpression(lessThan, "LessThan");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(LessThanOrEqual lessThanOrEqual) {
        checkBinaryExpression(lessThanOrEqual, "LessThanOrEqual");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(LogicalAnd logicalAnd) {
        return checkLogicalExpression(logicalAnd);
    }

    @Override
    public Type visit(LogicalOr logicalOr) {
        return checkLogicalExpression(logicalOr);
    }

    @Override
    public Type visit(Multiplication multiplication) {
        return checkBinaryExpression(multiplication, "Multiplication");
    }

    @Override
    public Type visit(NotEqual notEqual) {
        checkBinaryExpression(notEqual, "NotEqual");
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(Subtraction subtraction) {
        return checkBinaryExpression(subtraction, "Subtraction");
    }

    @Override
    public Type visit(Identifier identifier) {
        if (declarations.exists(identifier)) {
            return declarations.find(identifier);
        }

        messages.addError(new UndefinedReference(identifier.getLineNumber(), identifier.toString()));
        return new Unknown();
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral) {
        return new Boolean(0, "boolean");
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral) {
        return new Integer(0, "integer");
    }

    @Override
    public Type visit(StringLiteral stringLiteral) {
        return new String(0,"string");
    }

    @Override
    public Type visit(GroupExpression groupExpression) {
        return groupExpression.getExpression().accept(this);
    }

    private Type checkBinaryExpression(Binary expression, java.lang.String operationName) {
        Type leftType = expression.getLeftExpression().accept(this);
        Type rightType = expression.getRightExpression().accept(this);

        if (leftType.toString().equals(rightType.toString())) {
            return leftType;
        }

        messages.addError(new InvalidOperandsTypeToOperator(expression.getLineNumber(), operationName));
        return new Unknown();
    }

    private Type checkLogicalExpression(Binary expression) {
        Type leftType = expression.getLeftExpression().accept(this);
        Type rightType = expression.getRightExpression().accept(this);

        if (!leftType.isBoolean()) {
            messages.addError(new TypeMismatch(expression.getLineNumber(), leftType.toString()));
            return new Unknown();
        }

        if (!rightType.isBoolean()) {
            messages.addError(new TypeMismatch(expression.getLineNumber(), rightType.toString()));
            return new Unknown();
        }

        return new Boolean(0, "boolean");
    }
}
