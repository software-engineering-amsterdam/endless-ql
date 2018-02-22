package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.BooleanValue;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.evaluate.Value;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.traverse.Visitor;

import java.util.ArrayList;
import java.util.List;

public class QLEvaluator implements Visitor<Value> {

    private Error errors = new Error();

    private List<Question> questions = new ArrayList<>();

    private SymbolTable symbolTable;

    /**
     * Constructor
     * @param symbolTable The state of the program
     */
    public QLEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * Evaluate the AST and get all questions
     * @param node The base AST node
     */
    public List<Question> getQuestions(ASTNode node) {
        node.accept(this);
        return this.questions;
    }

    @Override
    public Value visit(Addition node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.add(right);
    }

    @Override
    public Value visit(And node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.and(right);
    }

    @Override
    public Value visit(Division node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.divide(right);
    }

    @Override
    public Value visit(Equal node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isEqual(right);
    }

    @Override
    public Value visit(GreaterOrEqual node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isGreaterOrEqual(right);
    }

    @Override
    public Value visit(GreaterThan node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isGreaterThan(right);
    }

    @Override
    public Value visit(LessOrEqual node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isLessOrEqual(right);
    }

    @Override
    public Value visit(LessThan node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isLessThan(right);
    }

    @Override
    public Value visit(Multiplication node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.multiply(right);
    }

    @Override
    public Value visit(Negative node) {
        Value value = node.getValue().accept(this);
        return value.negate();
    }

    @Override
    public Value visit(NotEqual node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.isNotEqual(right);
    }

    @Override
    public Value visit(Not node) {
        Value value = node.getValue().accept(this);
        return value.not();
    }

    @Override
    public Value visit(Or node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.or(right);
    }

    @Override
    public Value visit(Positive node) {
        Value value = node.getValue().accept(this);
        return value.positive();
    }

    @Override
    public Value visit(Subtraction node) {
        Value left = node.getLhs().accept(this);
        Value right = node.getRhs().accept(this);
        return left.subtract(right);
    }

    @Override
    public Value visit(Bool node) {
        return new BooleanValue(node.isTrue());
    }

    @Override
    public Value visit(DateExpr node) {
        return null;
    }

    @Override
    public Value visit(Decimal node) {
        return null;
    }

    @Override
    public Value visit(Money node) {
        return null;
    }

    @Override
    public Value visit(Int node) {
        return new IntValue(node.getValue());
    }

    @Override
    public Value visit(Str node) {
        return null;
    }

    @Override
    public Value visit(Type node) {
        return null;
    }

    public T visit(Variable node) {
        this.stack.add(node.getLinkedQuestion().getValue());
    }

    @Override
    public Value visit(Condition node) {
        return null;
    }

    @Override
    public Value visit(Form node) {
        return null;
    }

    @Override
    public Value visit(Question node) {
        return null;
    }

    @Override
    public Value visit(Statement node) {
        return null;
    }

    @Override
    public Value visit(Statements node) {
        return null;
    }

    @Override
    public Value visit(BinaryOperator node) {
        return null;
    }

    @Override
    public Value visit(SingleNode node) {
        return null;
    }
}
