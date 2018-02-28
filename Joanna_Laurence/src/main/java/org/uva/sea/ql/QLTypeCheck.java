package org.uva.sea.ql;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.Neg;
import org.uva.sea.ql.parser.elements.expressions.Not;
import org.uva.sea.ql.parser.elements.expressions.Pos;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class QLTypeCheck extends Traverse {

    private boolean error = false;

    /**
     * Check types and exit program when errors are found
     * Log will be written to std err
     * @param node Do the type check for the node
     */
    public boolean doTypeCheck(Form node) {
        node.doTraversal(this, TraverseType.TOP_DOWN);
        return !error;
    }

    /**
     * Determine if the type is numeric (Money is also)
     * @param node The node that contains the type
     * @param type The type as string
     */
    private void checkIsNumber(DualNode node, NodeType type) {
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
     * @param lhsType The first type
     * @param rhsType The second type
     */
    private void checkIsSame(ASTNode node, Type lhsType, Type rhsType) {
        boolean exactlyTheSame = lhsType.equals(rhsType);
        boolean almostTheSame = IsBasicNumber(lhsType) && IsBasicNumber(rhsType);

        if(!exactlyTheSame && !almostTheSame)
            this.error(node);
    }


    //TODO: Log the line and column!
    //TODO: Add the locations int the AST node
    /**
     * Logs when an error occurred
     * @param node The node that caused an error
     */
    private void error(ASTNode node) {
        System.err.println("Incorrect type on line:" + node.getLine() + " column: " + node.getColumn());
        this.error = true;
    }

    /**
     * When the value set: The type of a question should be the same as the value
     * @param node The node that is inspected
     */
    public void doQuestion(Question node) {
        ASTNode value = node.getValue();

        if(value != null) {
            checkIsSame(node, node.getNodeType(), value.getType());
        }
    }

    /**
     * Only money, int and decimal could be negated
     * @param node The node that is inspected
     */
    public void doNeg(Neg node)  {
        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN || nodeType == NodeType.INTEGER || nodeType == NodeType.DECIMAL))
            this.error(node);
    }

    /**
     * Only money, int and decimal could be made positive
     * @param node The node that is inspected
     */
    public void doPos(Pos node) {
        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.MONEY || nodeType == NodeType.INTEGER || nodeType == NodeType.DECIMAL))
            this.error(node);
    }

    /**
     * The value of the expression could be false or true. Show or hide
     * @param node The node that is inspected
     */
    //TODO: The expression object has to expression inside?
    public void doCondition(Condition node) {
        NodeType nodeType = node.getExpression().getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN))
            this.error(node);
    }

    /**
     * Invert only can be done on boolean types
     * @param node The node that is inspected
     */
    public void doNot(Not node)  {
        NodeType nodeType = node.getType().getNodeType();
        if(!(nodeType == NodeType.BOOLEAN))
            this.error(node);
    }

    //TODO: Add test that 1 + 1.5 is valid
    /**
     * Types can be integer and decimal. Int is converted to decimal
     * @param node The node that is inspected
     */
    public void doOperation(DualNode node)  {
        NodeType lhsType = node.getLhs().getType().getNodeType();
        NodeType rhsType = node.getRhs().getType().getNodeType();
        checkIsNumber(node, lhsType);
        checkIsNumber(node, rhsType);
    }

    //TODO: Is 1.0 equal to 1?
    /**
     * Types have to be the same
     * @param node The node that is inspected
     */
    public void doLogical(DualNode node)  {
        Type lhsType = node.getLhs().getType();
        Type rhsType = node.getRhs().getType();
        checkIsSame(node, lhsType, rhsType);
    }
}