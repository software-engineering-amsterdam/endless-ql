package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.HashMap;
import java.util.Map;

public final class CheckDuplicateLabels extends BaseASTVisitor<Void> implements IQLStaticAnalysis {


    private final Messages messages = new Messages();

    private final Map<String, String> labels = new HashMap<>();

    private CheckDuplicateLabels() {

    }

    private void error(final Question node) {
        this.messages.addMessage("Label:" + node.getLabel() + " is already linked to another variable on line: " + node.getLine() + " column: " + node.getColumn(), MessageTypes.WARNING);
    }

    public Messages doCheck(final Form node) {
        node.accept(this);
        return this.messages;
    }

    public Void visit(final Question node) {
        super.visit(node);

        final String labelLink = this.labels.get(node.getLabel());
        final boolean labelLinkedToOtherVariable = (labelLink != null) && !labelLink.equals(node.getVariable().getVariableName());

        if (labelLinkedToOtherVariable) {
            this.error(node);
            return null;
        }

        this.linkQuestionVariableToLabel(node);
        return null;
    }

    private void linkQuestionVariableToLabel(final Question node) {
        this.labels.put(node.getLabel(), node.getVariable().getVariableName());
    }

    public static class Checker implements IQLStaticAnalysis {
        @Override
        public final Messages doCheck(final Form node) {
            final IQLStaticAnalysis checker = new CheckDuplicateLabels();
            return checker.doCheck(node);
        }
    }
}
