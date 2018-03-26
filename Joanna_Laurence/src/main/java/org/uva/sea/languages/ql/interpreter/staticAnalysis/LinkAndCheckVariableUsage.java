package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Iterates over the AST and add links between variables and questions
 * Checks if variables are not used before declared
 * Determine if variables are not double defined. Only in if and else can be the same questionData.
 */
public class LinkAndCheckVariableUsage extends BaseASTVisitor implements IQLStaticAnalysis {


    private final Collection<Variable> usedVariables = new ArrayList<>();

    private final Messages messages = new Messages();

    private Map<String, Question> variableMap = new HashMap<>();

    private LinkAndCheckVariableUsage() {
    }

    private void error(String error, ASTNode node) {
        this.messages.addMessage(error + " on line:  " + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    public Messages doCheck(Form node) {
        node.accept(this);

        this.linkVariableInformation();

        return this.messages;
    }

    private void linkVariableInformation() {
        for (Variable variable : this.usedVariables) {
            String variableName = variable.getVariableName();
            if (!this.variableMap.containsKey(variableName)) {
                this.error("Variable is not defined", variable);
                return;
            }

            variable.setLinkedQuestion(this.variableMap.get(variableName));
        }
    }

    @Override
    public Void visit(Variable node) {
        super.visit(node);
        this.usedVariables.add(node);
        return null;
    }

    @Override
    public Void visit(Question node) {
        String variableName = node.getVariable().getVariableName();
        if (this.variableMap.containsKey(variableName)) {
            this.error("Question already exists", node);
            return null;
        }

        //Add new questionData to the lookup
        this.variableMap.put(variableName, node);

        //Visit all siblings
        super.visit(node);

        return null;
    }

    @Override
    public Object visit(IfStatement node) {
        node.getExpression().accept(this);

        HashMap<String, Question> baseMap = new HashMap<>(this.variableMap);

        //It is allowed to have duplicate elements in the then and else. So run both with the base map
        HashMap<String, Question> thenMap = this.visitStatementsWithVariableMap(baseMap, node.getThen());
        HashMap<String, Question> elseMap = this.visitStatementsWithVariableMap(baseMap, node.getOtherwise());

        this.variableMap = this.combineVariableMap(baseMap, thenMap, elseMap);

        return null;

    }

    private HashMap<String, Question> visitStatementsWithVariableMap(final HashMap<String, Question> baseMap, Statements statementsToCheck) {

        HashMap<String, Question> result = new HashMap<>();
        if (statementsToCheck == null)
            return result;

        this.variableMap = new HashMap<>(baseMap);
        statementsToCheck.accept(this);
        result.putAll(this.variableMap);
        return result;
    }

    private Map<String, Question> combineVariableMap(Map<String, Question>... maps) {
        if (maps.length == 0)
            return new HashMap<>();

        Map<String, Question> result = new HashMap<>();
        for (Map<String, Question> map : maps) {
            result.putAll(map);
        }

        return result;
    }

    public static class Checker implements IQLStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IQLStaticAnalysis checker = new LinkAndCheckVariableUsage();
            return checker.doCheck(node);
        }
    }
}
