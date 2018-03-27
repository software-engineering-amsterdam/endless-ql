package ql.evaluator;

import ql.ast.Form;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.statements.Question;
import ql.ast.statements.Statement;
import ql.values.StringValue;
import ql.values.Undefined;
import ql.values.Value;
import ql.visitors.ExpressionVisitor;
import ql.visitors.FormVisitor;
import ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class Evaluator implements FormVisitor, StatementVisitor<Void>, ExpressionVisitor<Value> {
    private final ValueTable valueTable = new ValueTable();
    private List<Question> questions = new ArrayList<>();

    //<editor-fold desc="FormVisitor">
    @Override
    public void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
    }
    //</editor-fold>

    //<editor-fold desc="StatementVisitor">
    @Override
    public Void visit(Question question) {
        if (!valueTable.exists(question.getIdentifier())) {
            valueTable.add(question.getIdentifier(), new Undefined());
        }
        questions.add(question);
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="ExpressionVisitor">
    public Value visit(Addition addition) {
        Value leftOperand = addition.getLeftExpression().accept(this);
        Value rightOperand = addition.getRightExpression().accept(this);

        return leftOperand.add(rightOperand);
    }

    public Value visit(Division division) {
        Value leftOperand = division.getLeftExpression().accept(this);
        Value rightOperand = division.getRightExpression().accept(this);

        return leftOperand.divide(rightOperand);
    }

    public Value visit(Equal equal) {
        Value leftOperand = equal.getLeftExpression().accept(this);
        Value rightOperand = equal.getRightExpression().accept(this);
        return leftOperand.equal(rightOperand);
    }

    public Value visit(GreaterThan greaterThan) {
        Value leftOperand = greaterThan.getLeftExpression().accept(this);
        Value rightOperand = greaterThan.getRightExpression().accept(this);
        return leftOperand.greaterThan(rightOperand);
    }

    public Value visit(GreaterThanOrEqual greaterThanOrEqual) {
        Value leftOperand = greaterThanOrEqual.getLeftExpression().accept(this);
        Value rightOperand = greaterThanOrEqual.getRightExpression().accept(this);
        return leftOperand.greaterThanOrEqual(rightOperand);
    }

    public Value visit(LessThan lessThan) {
        Value leftOperand = lessThan.getLeftExpression().accept(this);
        Value rightOperand = lessThan.getRightExpression().accept(this);
        return leftOperand.lessThan(rightOperand);
    }

    public Value visit(LessThanOrEqual lessThanOrEqual) {
        Value leftOperand = lessThanOrEqual.getLeftExpression().accept(this);
        Value rightOperand = lessThanOrEqual.getRightExpression().accept(this);
        return leftOperand.lessThanOrEqual(rightOperand);
    }

    public Value visit(LogicalAnd logicalAnd) {
        Value leftOperand = logicalAnd.getLeftExpression().accept(this);
        Value rightOperand = logicalAnd.getRightExpression().accept(this);
        return leftOperand.and(rightOperand);
    }

    public Value visit(LogicalOr logicalOr) {
        Value leftOperand = logicalOr.getLeftExpression().accept(this);
        Value rightOperand = logicalOr.getRightExpression().accept(this);
        return leftOperand.or(rightOperand);
    }

    public Value visit(Multiplication multiplication) {
        Value leftOperand = multiplication.getLeftExpression().accept(this);
        Value rightOperand = multiplication.getRightExpression().accept(this);
        return leftOperand.multiply(rightOperand);
    }

    public Value visit(NotEqual notEqual) {
        Value leftOperand = notEqual.getLeftExpression().accept(this);
        Value rightOperand = notEqual.getRightExpression().accept(this);
        return leftOperand.notEqual(rightOperand);
    }

    public Value visit(Subtraction subtraction) {
        Value leftOperand = subtraction.getLeftExpression().accept(this);
        Value rightOperand = subtraction.getRightExpression().accept(this);
        return leftOperand.subtract(rightOperand);
    }

    // TODO implement this method
    public Value visit(Identifier identifier) {
        return null;
    }

    // TODO implement this method
    public Value visit(StringLiteral stringLiteral) {
        return new StringValue(stringLiteral.getValue());
    }
    //</editor-fold>

    public List<Question> questions() {
        return questions;
    }
    public ValueTable valueTable() {
        return valueTable;
    }
}
