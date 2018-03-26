package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Relation;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

public class CircularExpressionDependencies extends BaseASTVisitor<Void> implements IQLStaticAnalysis {


    private final Messages messages = new Messages();

    private final Relation<String> relations = new Relation<>();

    private String question = null;

    @Override
    public final Messages doCheck(final Form node) {
        node.accept(this);

        for (final String circularRelation : this.relations.getCircularRelations())
            this.messages.addMessage("Circular dependency with " + circularRelation, MessageTypes.ERROR);

        return this.messages;
    }

    public final Void visit(final Variable node) {
        if (this.question == null)
            return null;

        this.relations.addRelation(this.question, node.getVariableName());
        return null;
    }

    public final Void visit(final Question node) {

        this.question = node.getVariable().getVariableName();
        this.linkRelationQuestionToQuestionExpression(node);
        this.question = null;

        return null;
    }

    private void linkRelationQuestionToQuestionExpression(final Question node) {
        if (node.getValue() != null)
            node.getValue().accept(this);
    }

    public static class Checker implements IQLStaticAnalysis {
        @Override
        public final Messages doCheck(final Form node) {
            final IQLStaticAnalysis checker = new CircularExpressionDependencies();
            return checker.doCheck(node);
        }
    }
}
