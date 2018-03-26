package org.uva.sea.languages.ql.parser.visitor;

import org.uva.sea.languages.ql.parser.elements.*;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.*;
import org.uva.sea.languages.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.languages.ql.parser.nodeTypes.SingleNode;

public abstract class BaseASTVisitor<T> implements IASTVisitor<T> {

    public T visit(final Addition node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final And node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Division node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Equal node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final GreaterOrEqual node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final GreaterThan node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final LessOrEqual node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final LessThan node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Multiplication node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Negative node) {
        return this.visit((SingleNode) node);
    }

    public T visit(final NotEqual node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Not node) {
        return this.visit((SingleNode) node);
    }

    public T visit(final Or node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Positive node) {
        return this.visit((SingleNode) node);
    }

    public T visit(final Subtraction node) {
        return this.visit((BinaryOperator) node);
    }

    public T visit(final Bool node) {
        return null;
    }

    public T visit(final DateExpr node) {
        return null;
    }

    public T visit(final Decimal node) {
        return null;
    }

    public T visit(final Money node) {
        return null;
    }

    public T visit(final Int node) {
        return null;
    }

    public T visit(final Str node) {
        return null;
    }

    public T visit(final Type node) {
        return null;
    }

    public T visit(final Variable node) {
        return null;
    }

    public T visit(final IfStatement node) {
        node.getExpression().accept(this);
        node.getThenBlock().accept(this);
        if (node.getOtherwiseBlock() != null)
            node.getOtherwiseBlock().accept(this);
        return null;
    }

    public final T visit(final Form node) {
        return node.getStatements().accept(this);
    }

    public T visit(final Question node) {
        if (node.getValue() != null)
            node.getValue().accept(this);

        node.getNodeType().accept(this);
        node.getVariable().accept(this);
        return null;
    }


    public T visit(final Statements node) {
        for (final ASTNode statement : node.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    public final T visit(final BinaryOperator node) {
        node.getLeftHandSide().accept(this);
        node.getRightHandSide().accept(this);
        return null;
    }

    public final T visit(final SingleNode node) {
        return node.getValue().accept(this);
    }
}
