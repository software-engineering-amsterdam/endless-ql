package org.uva.sea.ql;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.traverse.BaseVisitor;

public class QLTypeCheck extends BaseVisitor<Void> {

    private Error errors = new Error();

    /**
     * Check types and exit program when errors are found
     * Log will be written to std err
     * @param node Do the type check for the node
     */
    public Error doTypeCheck(Form node) {
        node.accept(this);
        return this.errors;
    }

    /**
     * Check if the types are the same
     * @param node The node to is checked
     */
    private void checkLogicalOperator(BinaryOperator node) {
        ASTNode lhs = node.getLhs();
        NodeType lhsType = lhs.getType().getNodeType();

        ASTNode rhs = node.getRhs();
        NodeType rhsType = rhs.getType().getNodeType();

        if(!lhsType.isTypeCompatible(rhsType))
            this.error(node);
    }


    /**
     * Logs when an error occurred
     * @param node The node that caused an error
     */
    private void error(ASTNode node) {
        this.errors.addError("Incorrect type on line:" + node.getLine() + " column: " + node.getColumn());
    }

    /**
     *
     * @param node The AST node
     * @return If the node is of type number
     */
    private boolean isNodeNumber(ASTNode node) {
        NodeType type = node.getType().getNodeType();
        return type.isNumber();
    }

    /**
     * Both sides have to be numbers
     */
    private void checkBinaryOperatorNumbers(BinaryOperator node) {
        if(!(isNodeNumber(node.getLhs()) && isNodeNumber(node.getRhs())))
            this.error(node);
    }

    /**
     * When the value set: The type of a question should be the same as the value
     * @param node The node that is inspected
     */
    public Void visit(Question node) {
        super.visit(node);

        ASTNode value = node.getDefaultValue();
        if(value != null) {
            NodeType questionType = node.getNodeType().getNodeType();
            NodeType valueType = value.getType().getNodeType();
            if(!questionType.isTypeCompatible(valueType))
                this.error(node);
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
        super.visit(node);

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
        super.visit(node);

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
