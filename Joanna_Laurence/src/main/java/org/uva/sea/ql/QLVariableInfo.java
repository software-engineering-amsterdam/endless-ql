package org.uva.sea.ql;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Condition;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
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

    private void error(String error, ASTNode node) {
        System.err.println(error + " on line: column: ");
        this.error = true;
    }

    public boolean addVariableInformation(ASTNode node) {
        node.traverse(this);
        return !error;
    }

    public void doVar(Var node) {
        //Questions should not already exist
        String variableName = node.getVariableName();
        if(!variableMap.containsKey(variableName)) {
            this.error("Variable is not defined", node);
            return;
        }

        node.setLinkedQuestion(variableMap.get(variableName));
    }

    public void doQuestion(Question node) {
        //Questions should not already exist
        String variableName = node.getVariable().getVariableName();
        if(variableMap.containsKey(variableName)) {
            this.error("Question already exist", node);
            return;
        }

        //Add new question to the lookup
        variableMap.put(variableName, node);
    }

}
