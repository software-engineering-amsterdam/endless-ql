package org.uva.sea.ql;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.types.Var;
import org.uva.sea.ql.traverse.Traverse;

import java.util.HashMap;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 */
public class QLVariableInfo extends Traverse {

    private Map<String, Question> variableMap = new HashMap<String, Question>();

    private Boolean error = false;

    //TODO: Log the line and column!
    //TODO: Add the locations int the AST node
    private void error(String error, ASTNode node) {
        System.err.println(error + " on line:  " + node.getLine() + " column: " + node.getColumn());
        this.error = true;
    }

    /**
     * Checks correct variable usage and links variables to questions
     * @param node The root node of the AST that needs to be checked
     * @return If an error occurred
     */
    public boolean addVariableInformation(Form node) {
        node.doTraversal(this, TraverseType.TOP_DOWN);
        return !error;
    }

    /**
     * Variables have to be defined before used
     * @param node The var node in the AST that is traversed
     */
    public void doVar(Var node) {
        //Questions should not already exist
        String variableName = node.getVariableName();
        if(!variableMap.containsKey(variableName)) {
            this.error("Variable is not defined", node);
            return;
        }

        node.setLinkedQuestion(variableMap.get(variableName));
    }

    /**
     * Questions should not be defined yet. Map the question by its name
     * @param node The question node in the AST that is traversed
     */
    public void doQuestion(Question node) {
        //Questions should not already exist
        String variableName = node.getVariable().getVariableName();
        if(variableMap.containsKey(variableName)) {
            this.error("Question already exists", node);
            return;
        }

        //Add new question to the lookup
        variableMap.put(variableName, node);
    }
}
