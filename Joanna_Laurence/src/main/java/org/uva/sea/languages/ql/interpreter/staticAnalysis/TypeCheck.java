package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.types.*;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.SpecificationKey;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.TypeCheckSpecification;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.HashMap;

public class TypeCheck extends BaseASTVisitor<NodeType> implements IStaticAnalysis<Form> {

    private Messages errors = new Messages();

    private HashMap<SpecificationKey, NodeType> typeCheckSpecification;

    /**
     * Hide constructor
     */
    private TypeCheck() {
        TypeCheckSpecification typeCheckSpecification = new TypeCheckSpecification();
        this.typeCheckSpecification = typeCheckSpecification.getSpecification();
    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis<Form> {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis<Form> checker = new TypeCheck();
            return checker.doCheck(node);
        }
    }

    /**
     * -     * Logs when an error occurred
     * -     * @param node The node that caused an error
     * -
     */
    private void error(ASTNode node) {
        this.errors.addMessage("Incorrect type on line:" + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    /**
     * Check types and exit program when errors are found
     * Log will be written to std err
     *
     * @param node Do the type check for the node
     */
    public Messages doCheck(Form node) {
        node.accept(this);
        return this.errors;
    }

    /**
     * Determine new type, and return error when the operation cannot be done
     *
     * @param node    The node that is checked
     * @param leftHandSideType Left hand side type, or the first type
     * @param rightHandSideType Right hand side type, or NodeType.UNKNOWN when only one type is needed
     * @return The new type
     */
    private NodeType getNodeTypeAndReportErrors(ASTNode node, NodeType leftHandSideType, NodeType rightHandSideType) {
        if (leftHandSideType == NodeType.INVALID || rightHandSideType == NodeType.INVALID)
            return NodeType.INVALID;

        NodeType returnType = this.typeCheckSpecification.get(new SpecificationKey(node.getClass(), leftHandSideType, rightHandSideType));
        if (returnType == null) {
            this.error(node);
            return NodeType.INVALID;
        }
        return returnType;
    }

    @Override
    public NodeType visit(Addition node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(And node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Division node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Equal node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(GreaterOrEqual node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(GreaterThan node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(LessOrEqual node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(LessThan node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Multiplication node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Negative node) {
        NodeType type = node.getType().accept(this);
        return getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(NotEqual node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Not node) {
        NodeType type = node.getType().accept(this);
        return getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(Type node) {
        return node.getNodeType();
    }


    @Override
    public NodeType visit(Or node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Positive node) {
        NodeType type = node.getType().accept(this);
        return getNodeTypeAndReportErrors(node, type, NodeType.UNKNOWN);
    }

    @Override
    public NodeType visit(Subtraction node) {
        NodeType leftHandSideType = node.getLeftHandSide().accept(this);
        NodeType rightHandSideType = node.getRightHandSide().accept(this);
        return getNodeTypeAndReportErrors(node, leftHandSideType, rightHandSideType);
    }

    @Override
    public NodeType visit(Variable node) {
        return node.getType().getNodeType();
    }

    @Override
    public NodeType visit(Bool node) {
        return NodeType.BOOLEAN;
    }

    @Override
    public NodeType visit(DateExpr node) {
        return NodeType.DATE;
    }

    @Override
    public NodeType visit(Decimal node) {
        return NodeType.DECIMAL;
    }

    @Override
    public NodeType visit(Money node) {
        return node.getType().getNodeType();
    }

    @Override
    public NodeType visit(Int node) {
        return NodeType.INTEGER;
    }

    @Override
    public NodeType visit(Str node) {
        return NodeType.STRING;
    }

    @Override
    public NodeType visit(Question node) {
        NodeType questionType = node.getType().getNodeType();
        ASTNode valueNode = node.getValue();

        if (valueNode != null) {
            NodeType valueType = valueNode.accept(this);
            if (questionType != valueType) {
                error(node);
                return NodeType.INVALID;
            }
        }

        return questionType;
    }
}