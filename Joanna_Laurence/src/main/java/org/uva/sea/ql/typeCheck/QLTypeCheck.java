package org.uva.sea.ql.typeCheck;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class QLTypeCheck extends Traverse {

    private boolean error = false;

    /**
     * Check types and exit program when errors are found
     * Log will be written to std err
     * @param node Do the type check for the node
     */
    public boolean doTypeCheck(ASTNode node) {
        node.traverse(this);
        return !error;
    }

    /**
     * Determine if the type is numeric
     * @param node The node that contains the type
     * @param type The type as string
     */
    private void checkIsNumber(DualNode node, String type) {
        if(!(type.equals("integer") || type.equals("decimal")))
            this.error(node);
    }

    /**
     * Check if the types are the same
     * @param node The node to is checked
     * @param lhsType The first type
     * @param rhsType The second type
     */
    private void checkIsSame(DualNode node, String lhsType, String rhsType) {
        if(lhsType.equals(rhsType))
            this.error(node);
    }


    //TODO: Log the line and column!
    //TODO: Add the locations int the AST node
    /**
     * Logs when an error occurred
     * @param node The node that caused an error
     */
    private void error(ASTNode node) {
        System.err.println("Incorrect type on line: column: ");
        this.error = true;
    }

    /**
     * When the value set: The type of a question should be the same as the value
     * @param node The node that is inspected
     */
    public void doQuestion(Question node) {
        ASTNode value = node.getValue();
        if(value != null && node.getType() != value.getType())
            this.error(node);
    }

    /**
     * Only money, int and decimal could be negated
     * @param node The node that is inspected
     */
    public void doNeg(Neg node)  {
        String nodeType = node.getType().getNodeType();
        if(!(nodeType.equals("money") || nodeType.equals("integer") || nodeType.equals("decimal")))
            this.error(node);
    }

    /**
     * Only money, int and decimal could be made positive
     * @param node The node that is inspected
     */
    public void doPos(Pos node) {
        String nodeType = node.getType().getNodeType();
        if(!(nodeType.equals("money") || nodeType.equals("integer") || nodeType.equals("decimal")))
            this.error(node);
    }

    /**
     * The value of the expression could be false or true. Show or hide
     * @param node The node that is inspected
     */
    //TODO: The expression object has to expression inside?
    public void doCondition(Condition node) {
        String nodeType = node.getExpression().getType().getNodeType();
        if(!(nodeType.equals("boolean")))
            this.error(node);
    }

    /**
     * Invert only can be done on boolean types
     * @param node The node that is inspected
     */
    public void doNot(Not node)  {
        String nodeType = node.getType().getNodeType();
        if(!(nodeType.equals("boolean")))
            this.error(node);
    }

    //TODO: Add test that 1 + 1.5 is valid
    /**
     * Types can be integer and decimal. Int is converted to decimal
     * @param node The node that is inspected
     */
    public void doOperation(DualNode node)  {
        String lhsType = node.getLhs().getType().getNodeType();
        String rhsType = node.getRhs().getType().getNodeType();
        checkIsNumber(node, lhsType);
        checkIsNumber(node, rhsType);
    }

    //TODO: Is 1.0 equal to 1?
    /**
     * Types have to be the same
     * @param node The node that is inspected
     */
    public void doLogical(DualNode node)  {
        String lhsType = node.getLhs().getType().getNodeType();
        String rhsType = node.getRhs().getType().getNodeType();
        checkIsSame(node, lhsType, rhsType);
    }
}
