package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.parser.elements.*;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.parser.elements.types.Variable;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 * Determine if variables are not double defined. Only in if and else can be the same questionData.
 */
public class LinkAndCheckVariableUsage extends BaseASTVisitor implements IStaticAnalysis<Form> {

    /**
     * Contain what questions is related to what variable
     */
    private Map<String, Question> variableMap = new HashMap<>();

    /**
     * Contains variables that are used in the program. They are linked to questions
     * at the send of the evaluation
     */
    private List<Variable> usedVariables = new ArrayList<>();

    /**
     *
     */
    private Messages messages = new Messages();

    /**
     * Hide constructor
     */
    private  LinkAndCheckVariableUsage() {
    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis<Form> {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis checker = new LinkAndCheckVariableUsage();
            return checker.doCheck(node);
        }
    }

    /**
     * @param error Error that occur
     * @param node  The node that caused the error
     */
    private void error(String error, ASTNode node) {
        this.messages.addMessage(error + " on line:  " + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    /**
     * Checks correct variable usage and links variables to questions
     *
     * @param node The root node of the AST that needs to be checked
     * @return If an error occurred
     */
    public Messages doCheck(Form node) {
        node.accept(this);

        linkVariableInformation();

        return this.messages;
    }

    /**
     * Link all variables to the correct questionData
     * Add error when it is not defined
     */
    private void linkVariableInformation() {
        for(Variable variable : this.usedVariables) {
            String variableName = variable.getVariableName();
            if (!variableMap.containsKey(variableName)) {
                this.error("Variable is not defined", variable);
                return;
            }

            variable.setLinkedQuestion(variableMap.get(variableName));
        }
    }

    /**
     * Variables have to be defined before used
     *
     * @param node The var node in the AST that is traversed
     */
    @Override
    public Void visit(Variable node) {
        super.visit(node);
        this.usedVariables.add(node);
        return null;
    }

    /**
     * Questions should not be defined yet. Map the questionData by its name
     *
     * @param node The questionData node in the AST that is traversed
     */
    @Override
    public Void visit(Question node) {
        String variableName = node.getVariable().getVariableName();
        if(variableMap.containsKey(variableName)) {
            this.error("Question already exists", node);
            return null;
        }

        //Add new questionData to the lookup
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
