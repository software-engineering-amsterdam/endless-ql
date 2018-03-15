package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Relation;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

public class CircularExpressionDependencies extends BaseASTVisitor<Void> implements IStaticAnalysis<Form> {


    private Messages messages = new Messages();

    private Relation<String> relations = new Relation<>();

    private String question = null;

    @Override
    public Messages doCheck(Form node) {
        node.accept(this);

        for (String circularRelation : this.relations.getCircularRelations())
            this.messages.addMessage("Circular dependency with " + circularRelation, MessageTypes.ERROR);

        return this.messages;
    }

    public Void visit(Variable node) {
        if (this.question == null)
            return null;

        this.relations.addRelation(this.question, node.getVariableName());
        return null;
    }

    public Void visit(Question node) {

        this.setRelationQuestion(node.getVariable().getVariableName());
        linkRelationQuestionToQuestionExpression(node);
        this.setRelationQuestion(null);

        return null;
    }

    /**
     * @param node
     */
    private void linkRelationQuestionToQuestionExpression(Question node) {
        if (node.getValue() != null)
            node.getValue().accept(this);
    }

    /**
     * @param question Null for none.
     */
    private void setRelationQuestion(String question) {
        this.question = question;
    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis<Form> {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis<Form> checker = new CircularExpressionDependencies();
            return checker.doCheck(node);
        }
    }
}
