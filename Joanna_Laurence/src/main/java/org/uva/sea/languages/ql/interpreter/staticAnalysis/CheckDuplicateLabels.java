package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.HashMap;

public class CheckDuplicateLabels extends BaseASTVisitor<Void> implements IStaticAnalysis {


    private Messages warnings = new Messages(MessageTypes.WARNING);

    /**
     * Labels that are associated with variables
     */
    private HashMap<String, String> labels = new HashMap<>();

    /**
     * Hide constructor
     */
    private CheckDuplicateLabels() {

    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis checker = new CheckDuplicateLabels();
            return checker.doCheck(node);
        }
    }

    /**
     * Report warning
     *
     * @param node The node that caused the warning
     */
    private void error(Question node) {
        this.warnings.addMessage("Label:" + node.getLabel() + " is already linked to another variable on line: " + node.getLine() + " column: " + node.getColumn());
    }

    /**
     * Perform the check
     *
     * @param node The form node
     * @return Warnings
     */
    public Messages doCheck(Form node) {
        node.accept(this);
        return this.warnings;
    }

    /**
     * Check the questions
     *
     * @param node
     * @return
     */
    public Void visit(Question node) {
        super.visit(node);

        String labelLink = this.labels.get(node.getLabel());
        boolean labelLinkedToOtherVariable = labelLink != null && !labelLink.equals(node.getVariable().getVariableName());

        if (labelLinkedToOtherVariable) {
            this.error(node);
            return null;
        }

        linkQuestionVariableToLabel(node);
        return null;
    }

    /**
     * Link label to variable
     *
     * @param node
     */
    private void linkQuestionVariableToLabel(Question node) {
        this.labels.put(node.getLabel(), node.getVariable().getVariableName());
    }
}
