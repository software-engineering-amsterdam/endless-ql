package org.uva.sea.ql;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.types.Variable;
import org.uva.sea.ql.visitor.BaseVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 */
public class QLVariableInfo extends BaseVisitor {

    private Map<String, Question> variableMap = new HashMap<String, Question>();

    private Errors errors = new Errors();

    /**
     *
     * @param error
     * @param node
     */
    private void error(String error, ASTNode node) {
        this.errors.addError(error + " on line:  " + node.getLine() + " column: " + node.getColumn());
    }

    /**
     * Checks correct variable usage and links variables to questions
     * @param node The root node of the AST that needs to be checked
     * @return If an error occurred
     */
    public Errors addVariableInformation(Form node) {
        this.errors.clear();

        node.accept(this);
        return this.errors;
    }

    /**
     * Variables have to be defined before used
     * @param node The var node in the AST that is traversed
     */
    @Override
    public Void visit(Variable node) {
        super.visit(node);

        //Questions should not already exist
        String variableName = node.getVariableName();
        if(!variableMap.containsKey(variableName)) {
            this.error("Variable is not defined", node);
            return null;
        }

        node.setLinkedQuestion(variableMap.get(variableName));
        return null;
    }

    /**
     * Questions should not be defined yet. Map the question by its name
     * @param node The question node in the AST that is traversed
     */
    @Override
    public Void visit(Question node) {
        //Questions should not already exist
        String variableName = node.getVariable().getVariableName();

        //Add new question to the lookup
        variableMap.put(variableName, node);

        //Visit all siblings
        super.visit(node);

        return null;
    }
}
