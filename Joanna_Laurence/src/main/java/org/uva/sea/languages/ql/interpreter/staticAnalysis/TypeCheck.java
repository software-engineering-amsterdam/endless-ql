package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.SpecificationKey;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.TypeCheckSpecification;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.*;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.Map;


public final class TypeCheck extends BaseASTVisitor<NodeType> implements IQLStaticAnalysis {

    private final Messages errors = new Messages();

    private final Map<SpecificationKey, NodeType> typeCheckSpecification;


    private TypeCheck() {
        final TypeCheckSpecification typeCheckSpecification = new TypeCheckSpecification();
        this.typeCheckSpecification = typeCheckSpecification.getSpecification();
    }


    private void error(final ASTNode node) {
        this.errors.addMessage("Incorrect type on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    public Messages doCheck(final Form node) {
        node.accept(this);
        return this.errors;
    }

    private NodeType getNodeTypeAndReportErrors(final ASTNode node, final NodeType leftHandSideType, final NodeType rightHandSideType) {
        if ((leftHandSideType == NodeType.INVALID) || (rightHandSideType == NodeType.INVALID))
            return NodeType.INVALID;

        final NodeType returnType = this.typeCheckSpecification.get(new SpecificationKey(node.getClass(), leftHandSideType, rightHandSideType));
        if (returnType == null) {
            this.error(node);
            return NodeType.INVALID;
        }
        return returnType;
    }

    @Override
    public NodeType visit(final Addition node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final And node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Division node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Equal node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final GreaterOrEqual node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final GreaterThan node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final LessOrEqual node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final LessThan node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Multiplication node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Negative node) {
        final NodeType type = node.getType().accept(this);
        return this.getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(final NotEqual node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Not node) {
        final NodeType type = node.getType().accept(this);
        return this.getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(final Type node) {
        return node.getNodeType();
    }

    @Override
    public NodeType visit(final Or node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Positive node) {
        final NodeType type = node.getType().accept(this);
        return this.getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(final Subtraction node) {
        final NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        final NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return this.getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(final Variable node) {
        return node.getType().getNodeType();
    }

    @Override
    public NodeType visit(final Bool node) {
        return NodeType.BOOLEAN;
    }

    @Override
    public NodeType visit(final DateExpr node) {
        return NodeType.DATE;
    }

    @Override
    public NodeType visit(final Decimal node) {
        return NodeType.DECIMAL;
    }

    @Override
    public NodeType visit(final Money node) {
        return node.getType().getNodeType();
    }

    @Override
    public NodeType visit(final Int node) {
        return NodeType.INTEGER;
    }

    @Override
    public NodeType visit(final Str node) {
        return NodeType.STRING;
    }

    @Override
    public NodeType visit(final Question node) {
        final NodeType questionType = node.getType().getNodeType();
        final ASTNode valueNode = node.getValue();

        if (valueNode != null) {
            final NodeType valueType = valueNode.accept(this);
            if (questionType != valueType) {
                this.error(node);
                return NodeType.INVALID;
            }
        }

        return questionType;
    }


    public static class Checker implements IQLStaticAnalysis {
        @Override
        public final Messages doCheck(final Form node) {
            final IQLStaticAnalysis checker = new TypeCheck();
            return checker.doCheck(node);
        }
    }
}