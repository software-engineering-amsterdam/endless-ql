package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.HashMap;
import java.util.Map;

public class CheckIncorrectDuplicateQuestions extends BaseASTVisitor<Void> implements IQLStaticAnalysis {


    private final Messages errors = new Messages();

    private final Map<String, NodeType> variables = new HashMap<>();

    private CheckIncorrectDuplicateQuestions() {

    }

    private void error(Question node) {
        this.errors.addMessage(node.getVariable().getVariableName() + " is different defined on line: " + node.getLine() + " column: " + node.getColumn(), MessageTypes.ERROR);
    }

    public Messages doCheck(Form node) {
        node.accept(this);
        return this.errors;
    }

    public Void visit(Question node) {
        super.visit(node);

        NodeType linkedNodeType = this.variables.get(node.getVariable().getVariableName());
        boolean incorrectNodeTypeLinked = (linkedNodeType != null) && (linkedNodeType != node.getNodeType().getNodeType());

        if (incorrectNodeTypeLinked) {
            this.error(node);
            return null;
        }

        this.linkNodeTypeToVariable(node);
        return null;
    }

    private void linkNodeTypeToVariable(Question node) {
        this.variables.put(node.getLabel(), node.getNodeType().getNodeType());
    }

    public static class Checker implements IQLStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IQLStaticAnalysis checker = new CheckIncorrectDuplicateQuestions();
            return checker.doCheck(node);
        }
    }
}
