package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.*;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

public class ExpressionEvaluator extends BaseASTVisitor<Value> {

    private SymbolTable symbolTable = new SymbolTable();


    public Value evaluate(ASTNode node, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return node.accept(this);
    }

    @Override
    public Value visit(Addition node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.add(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(And node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.and(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Division node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.divide(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Equal node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isEqual(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(GreaterOrEqual node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isGreaterOrEqual(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(GreaterThan node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isGreaterThan(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(LessOrEqual node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isLessOrEqual(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(LessThan node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isLessThan(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Multiplication node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.multiply(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Negative node) {
        try {
            Value value = node.getValue().accept(this);
            return value.negate();
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(NotEqual node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.isNotEqual(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Not node) {
        try {
            Value value = node.getValue().accept(this);
            return value.not();
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Or node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.or(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Positive node) {
        try {
            Value value = node.getValue().accept(this);
            return value.positive();
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Subtraction node) {
        try {
            Value left = node.getLeftHandSide().accept(this);
            Value right = node.getRightHandSide().accept(this);
            return right.subtract(left);
        } catch (EvaluationException e) {
            return new ErrorValue(e.getMessage(), node.getLine(), node.getColumn());
        }
    }

    @Override
    public Value visit(Bool node) {
        return new BooleanValue(node.isTrue());
    }

    @Override
    public Value visit(DateExpr node) {
        return new DateValue(node.getDate());
    }

    @Override
    public Value visit(Decimal node) {
        return new DecimalValue(node.getValue());
    }

    @Override
    public Value visit(Money node) {
        return new MoneyValue(node.getCurrency(), node.getAmount());
    }

    @Override
    public Value visit(Int node) {
        return new IntValue(node.getValue());
    }

    @Override
    public Value visit(Str node) {
        return new StringValue(node.getValue());
    }

    @Override
    public Value visit(Type node) {
        return null;
    }

    @Override
    public Value visit(Variable node) {
        Value symbolValue = this.symbolTable.getValue(node.getVariableName());
        if (symbolValue != null)
            return symbolValue;

        if ((node.getLinkedQuestion() != null) && (node.getLinkedQuestion().getValue() != null)) {
            return node.getLinkedQuestion().getValue().accept(this);
        }

        return new UndefinedValue();
    }
}
