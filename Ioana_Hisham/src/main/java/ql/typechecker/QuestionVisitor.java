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
import ql.typechecker.messages.error.TypeMismatch;
import ql.typechecker.messages.warning.DuplicateLabel;
import ql.types.Type;
import ql.visitors.ExpressionVisitor;
import ql.visitors.FormVisitor;
import ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionVisitor implements FormVisitor, StatementVisitor<Void>, ExpressionVisitor<Type> {
    private final Messages messages;
    private final List<String> questionLabels;
    private final DeclarationTable declarations;

    public QuestionVisitor(Messages messages) {
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
        return null;
    }

    @Override
    public Type visit(Division division) {
        return null;
    }

    @Override
    public Type visit(Equal equal) {
        return null;
    }

    @Override
    public Type visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public Type visit(GreaterThanOrEqual greaterThanOrEqual) {
        return null;
    }

    @Override
    public Type visit(LessThan lessThan) {
        return null;
    }

    @Override
    public Type visit(LessThanOrEqual lessThanOrEqual) {
        return null;
    }

    @Override
    public Type visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public Type visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public Type visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public Type visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Type visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        return null;
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Type visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public Type visit(GroupExpression groupExpression) {
        return null;
    }
}
