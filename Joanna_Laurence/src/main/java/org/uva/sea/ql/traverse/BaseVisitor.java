package org.uva.sea.ql.traverse;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;

public abstract class BaseVisitor<T> implements Visitor<T> {

    public T visit(Addition node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(And node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Division node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Equal node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(GreaterOrEqual node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(GreaterThan node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(LessOrEqual node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(LessThan node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Multiplication node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Negative node)  {
        this.visit((SingleNode) node);
        return null;
    }

    public T visit(NotEqual node)  {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Not node)  {
        this.visit((SingleNode) node);
        return null;
    }

    public T visit(Or node) {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Positive node) {
        this.visit((SingleNode) node);
        return null;
    }

    public T visit(Subtraction node) {
        this.visit((BinaryOperator)node);
        return null;
    }

    public T visit(Bool node) {
        return null;
    }

    public T visit(DateExpr node) {
        return null;
    }

    public T visit(Decimal node) {
        return null;
    }

    public T visit(Money node) {
        return null;
    }

    public T visit(Int node) {
        return null;
    }

    public T visit(Str node) {
        return null;
    }

    public T visit(Type node) {
        return null;
    }

    public T visit(Variable node) {
        return null;
    }

    public T visit(IfStatement node) {
        node.getExpression().accept(this);
        node.getStatements().accept(this);
        return null;
    }

    public T visit(Form node) {
        node.getStatements().accept(this);
        return null;
    }

    public T visit(Question node) {
        if(node.getValue() != null)
            node.getValue().accept(this);

        node.getNodeType().accept(this);
        node.getVariable().accept(this);
        return null;
    }

    public T visit(Statement node) {
        node.getIfStatement().accept(this);
        node.getQuestion().accept(this);
        return null;
    }

    public T visit(Statements node) {
        for( ASTNode statement : node.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    public T visit(BinaryOperator node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        return null;
    }

    public T visit(SingleNode node) {
        node.getValue().accept(this);
        return null;
    }
}
