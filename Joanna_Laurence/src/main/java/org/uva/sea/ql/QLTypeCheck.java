package org.uva.sea.ql;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.traverse.BaseVisitor;

public class QLTypeCheck extends BaseVisitor<Void> {

    private boolean error = false;

    /**
     * Check types and exit program when errors are found
     * Log will be written to std err
     * @param node Do the type check for the node
     */
    public boolean doTypeCheck(Form node) {
        node.accept(this);
        return !error;
    }

    /**
     * Determine if the type is numeric (Money is also)
     * @param node The node that contains the type
     * @param type The type as string
     */
    private void checkIsNumber(ASTNode node, NodeType type) {
        if(!(type == NodeType.INTEGER || type == NodeType.DECIMAL || type == NodeType.MONEY))
            this.error(node);
    }

    /**
     * Determine if the type is numeric (Int/double)
     * @param type The type
     */
    private boolean IsBasicNumber(Type type) {
        return (type.getNodeType() == NodeType.INTEGER || type.getNodeType() == NodeType.DECIMAL);
    }


    /**
     * Check if the types are the same
     * @param node The node to is checked
     */
    private void checkLogicalOperator(BinaryOperator node) {
        Type lhsType = node.getLhs().getType();
        Type rhsType = node.getRhs().getType();
        checkTypesCompatible(node, lhsType, rhsType);
    }

    /**
     *
     * @param node The ASTNode
     * @param firstType The first type that is compared
     * @param secondType The second type that is compared
     */
    private void checkTypesCompatible(ASTNode node, Type firstType, Type secondType) {
        boolean exactlyTheSame = firstType.equals(secondType);
        boolean compatibleTypes = IsBasicNumber(firstType) && IsBasicNumber(secondType);
        if(!(exactlyTheSame && compatibleTypes))
            this.error(node);
    }


    /**
     * Logs when an error occurred
     * @param node The node that caused an error
     */
    private void error(ASTNode node) {
        System.err.println("Incorrect type on line:" + node.getLine() + " column: " + node.getColumn());
        this.error = true;
    }

    /**
     * Both sides have to be numbers
     */
    private void checkBinaryOperatorNumbers(BinaryOperator node) {
        checkIsNumber(node, node.getLhs().getType().getNodeType());
        checkIsNumber(node, node.getRhs().getType().getNodeType());
    }

    /**
     * When the value set: The type of a question should be the same as the value
     * @param node The node that is inspected
     */
    public Void visit(Question node) {
        super.visit(node);

        ASTNode value = node.getValue();
        if(value != null) {
            this.checkTypesCompatible(node, node.getNodeType(), value.getType());
        }
        return null;
    }


    @Override
    public Void visit(Addition node) {
        super.visit(node);
        this.checkBinaryOperatorNumbers(node);
        return null;
    }

    @Override
    public Void visit(And node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(Division node) {
        this.checkBinaryOperatorNumbers(node);
        return null;
    }

    @Override
    public Void visit(Equal node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(GreaterOrEqual node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(GreaterThan node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(LessOrEqual node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(LessThan node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    @Override
    public Void visit(Multiplication node) {
        super.visit(node);
        this.checkBinaryOperatorNumbers(node);
        return null;
    }

    /**
     * Only money, int and decimal could be negated
     * @param node The node that is inspected
     */
    public Void visit(Negative node)  {
        super.visit(node);

        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN || nodeType == NodeType.INTEGER || nodeType == NodeType.DECIMAL))
            this.error(node);
        return null;
    }

    @Override
    public Void visit(NotEqual node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }

    /**
     * Only money, int and decimal could be made positive
     * @param node The node that is inspected
     */
    public Void visit(Positive node) {
        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.MONEY || nodeType == NodeType.INTEGER || nodeType == NodeType.DECIMAL))
            this.error(node);
        return null;
    }

    @Override
    public Void visit(Subtraction node) {
        super.visit(node);
        this.checkBinaryOperatorNumbers(node);
        return null;
    }

    /**
     * The value of the expression could be false or true. Show or hide
     * @param node The node that is inspected
     */
    public Void visit(Condition node) {
        super.visit(node);

        NodeType nodeType = node.getExpression().getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN))
            this.error(node);
        return null;
    }

    /**
     * Invert only can be done on boolean types
     * @param node The node that is inspected
     */
    public Void visit(Not node)  {
        super.visit(node);

        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN))
            this.error(node);
        return null;
    }

    @Override
    public Void visit(Or node) {
        super.visit(node);
        this.checkLogicalOperator(node);
        return null;
    }
}
