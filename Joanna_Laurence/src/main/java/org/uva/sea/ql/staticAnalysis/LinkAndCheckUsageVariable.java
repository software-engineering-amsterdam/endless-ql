package org.uva.sea.ql.staticAnalysis;

import org.uva.sea.ql.dataObject.MessageTypes;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.types.Variable;
import org.uva.sea.ql.staticAnalysis.helpers.Messages;
import org.uva.sea.ql.visitor.BaseASTVisitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 */
public class LinkAndCheckUsageVariable extends BaseASTVisitor implements IStaticAnalysis {

    /**
     * Contain what questions is related to what variable
     */
    private Map<String, Question> variableMap = new HashMap<>();

    /**
     *
     */
    private Messages messages = new Messages(MessageTypes.ERROR);

    /**
     * @param error Error that occur
     * @param node  The node that caused the error
     */
    private void error(String error, ASTNode node) {
        this.messages.addMessage(error + " on line:  " + node.getLine() + " column: " + node.getColumn());
    }

    /**
     * Checks correct variable usage and links variables to questions
     *
     * @param node The root node of the AST that needs to be checked
     * @return If an error occurred
     */
    public Messages doCheck(Form node) {
        this.messages.clear();

        node.accept(this);
        return this.messages;
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
        if(variableMap.containsKey(variableName)) {
            this.error("Question already exists", node);
            return null;
        }

        //Add new question to the lookup
        variableMap.put(variableName, node);

        //Visit all siblings
        super.visit(node);

        return null;
    }

    @Override
    public Object visit(IfStatement node) {
        node.getExpression().accept(this);

        HashMap<String, Question> baseMap = new HashMap<>(this.variableMap);

        //It is allowed to have duplicate elements in the then and else. So run both with the base map
        HashMap<String, Question> thenMap = visitStatementsWithVariableMap(baseMap, node.getThen());
        HashMap<String, Question> elseMap = visitStatementsWithVariableMap(baseMap, node.getOtherwise());

        this.variableMap = this.combineVariableMap(baseMap, thenMap, elseMap);

        return null;

    }

    /**
     * Visits statements with a base map
     * @param baseMap The base map
     * @param statementsToCheck Statements to check
     * @return A new HashSet that contains all the mappings from base and statements
     */
    private HashMap<String, Question> visitStatementsWithVariableMap(final HashMap<String, Question> baseMap, Statements statementsToCheck) {

        HashMap<String, Question> result = new HashMap<>();
        if (statementsToCheck == null)
            return result;

        this.variableMap = new HashMap<>(baseMap);
        statementsToCheck.accept(this);
        result.putAll(this.variableMap);
        return result;
    }

    /**
     * Combines variable map
     * @param maps Variable maps
     * @return New map with all elements combined
     */
    private Map<String, Question> combineVariableMap(Map<String, Question>... maps) {
        if(maps.length == 0)
            return new HashMap<>();

        Map<String, Question> result = new HashMap<>();
        for(Map<String, Question> map : maps) {
            result.putAll(map);
        }

        return result;
    }
}
