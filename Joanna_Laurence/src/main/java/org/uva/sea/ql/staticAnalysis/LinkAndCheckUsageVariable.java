package org.uva.sea.ql.staticAnalysis;

import org.uva.sea.ql.dataObject.MessageTypes;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.types.Variable;
import org.uva.sea.ql.staticAnalysis.helpers.Messages;
import org.uva.sea.ql.visitor.BaseASTVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 */
public class LinkAndCheckUsageVariable extends BaseASTVisitor implements IStaticAnalysis {

    /**
     * Contain what questions is related to what variable
     */
    private Map<String, Question> variableMap = new HashMap<String, Question>();

    /**
     * Messages that occur during this process
     */
    private Messages errors = new Messages(MessageTypes.ERROR);

    /**
     * @param error Error that occur
     * @param node  The node that caused the error
     */
    private void error(String error, ASTNode node) {
        this.errors.addMessage(error + " on line:  " + node.getLine() + " column: " + node.getColumn());
    }

    /**
     * Checks correct variable usage and links variables to questions
     *
     * @param node The root node of the AST that needs to be checked
     * @return If an error occurred
     */
    public Messages doCheck(Form node) {
        this.errors.clear();

        node.accept(this);
        return this.errors;
    }

    /**
     * Variables have to be defined before used
     *
     * @param node The var node in the AST that is traversed
     */
    @Override
    public Void visit(Variable node) {
        super.visit(node);

        //Questions should not already exist
        String variableName = node.getVariableName();
        if (!variableMap.containsKey(variableName)) {
            this.error("Variable is not defined", node);
            return null;
        }

        node.setLinkedQuestion(variableMap.get(variableName));
        return null;
    }

    /**
     * Questions should not be defined yet. Map the question by its name
     *
     * @param node The question node in the AST that is traversed
     */
    @Override
    public Void visit(Question node) {
        String variableName = node.getVariable().getVariableName();
        variableMap.put(variableName, node);

        //Visit all siblings
        super.visit(node);

        return null;
    }
}
