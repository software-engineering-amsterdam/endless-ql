package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.*;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

public class ExpressionEvaluator extends BaseASTVisitor<Value> {

    private SymbolTable symbolTable = new SymbolTable();


    public final Value evaluate(final ASTNode node, final SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return node.accept(this);
    }

    @Override
    public final Value visit(final Addition node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.add(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final And node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.and(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Division node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.divide(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Equal node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isEqual(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final GreaterOrEqual node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isGreaterOrEqual(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final GreaterThan node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isGreaterThan(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final LessOrEqual node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isLessOrEqual(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final LessThan node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isLessThan(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Multiplication node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.multiply(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Negative node) {
        try {
            final Value value = node.getValue().accept(this);
            return value.negate();
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final NotEqual node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.isNotEqual(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Not node) {
        try {
            final Value value = node.getValue().accept(this);
            return value.not();
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Or node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.or(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Positive node) {
        try {
            final Value value = node.getValue().accept(this);
            return value.positive();
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Subtraction node) {
        try {
            final Value left = node.getLeftHandSide().accept(this);
            final Value right = node.getRightHandSide().accept(this);
            return right.subtract(left);
        } catch (final EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public final Value visit(final Bool node) {
        return new BooleanValue(node.isTrue());
    }

    @Override
    public final Value visit(final DateExpr node) {
        return new DateValue(node.getDate());
    }

    @Override
    public final Value visit(final Decimal node) {
        return new DecimalValue(node.getValue());
    }

    @Override
    public final Value visit(final Money node) {
        return new MoneyValue(node.getCurrency(), node.getAmount());
    }

    @Override
    public final Value visit(final Int node) {
        return new IntValue(node.getValue());
    }

    @Override
    public final Value visit(final Str node) {
        return new StringValue(node.getValue());
    }

    @Override
    public final Value visit(final Type node) {
        return null;
    }

    @Override
    public final Value visit(final Variable node) {
        final Value symbolValue = this.symbolTable.getValue(node.getVariableName());
        if (symbolValue != null)
            return symbolValue;

        if ((node.getLinkedQuestion() != null) && (node.getLinkedQuestion().getValue() != null)) {
            return node.getLinkedQuestion().getValue().accept(this);
        }

        return new UndefinedValue();
    }
}
