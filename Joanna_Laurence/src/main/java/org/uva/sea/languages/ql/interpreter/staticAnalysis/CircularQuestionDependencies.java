package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Relation;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.RelationEntity;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.IfStatement;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.Collection;

public final class CircularQuestionDependencies extends BaseASTVisitor<Void> implements IQLStaticAnalysis {

    private final Relation<String> dependencies = new Relation<>();

    private CircularQuestionDependencies() {

    }

    @Override
    public Messages doCheck(final Form node) {
        node.accept(this);
        return this.incorrectDependenciesToErrors(this.getIncorrectAsymmetricElements(this.dependencies));
    }

    private Messages incorrectDependenciesToErrors(final Relation<String> incorrectDependencies) {
        final Messages errors = new Messages();
        for (final RelationEntity<String> dependency : incorrectDependencies.getRelations())
            errors.addMessage(dependency.getKey() + " has a circular dependency with" + dependency.getValue(), MessageTypes.ERROR);
        return errors;
    }

    @Override
    public Void visit(final IfStatement ifNode) {
        final Collection<String> dependsOn = new ArrayList<>();
        ifNode.getExpression().accept(new BaseASTVisitor<Void>() {
            public Void visit(final Variable node) {
                if ((node.getLinkedQuestion() != null) && (node.getLinkedQuestion().getValue() == null))
                    dependsOn.add(node.getVariableName());

                return super.visit(node);
            }
        });

        final Collection<String> questions = new ArrayList<>();
        ifNode.getThenBlock().accept(new BaseASTVisitor<Void>() {
            public Void visit(final Question node) {
                questions.add(node.getVariable().getVariableName());
                return null;
            }
        });


        this.addRelations(questions, dependsOn, this.dependencies);

        super.visit(ifNode);
        return null;
    }

    private void addRelations(final Iterable<String> questions, final Iterable<String> dependsOn, final Relation<String> relation) {
        for (final String question : questions) {
            for (final String dependOn : dependsOn) {
                relation.addRelation(question, dependOn);
            }
        }
    }

    private Relation<String> getIncorrectAsymmetricElements(final Relation<String> relation) {
        final Relation<String> incorrectElements = new Relation<>();

        for (final RelationEntity<String> entry : relation.getRelations()) {
            if (relation.contains(entry.getValue(), entry.getKey()))
                incorrectElements.addRelation(entry.getKey(), entry.getValue());
        }

        return incorrectElements;
    }

    public static class Checker implements IQLStaticAnalysis {
        @Override
        public final Messages doCheck(final Form node) {
            final IQLStaticAnalysis checker = new CircularQuestionDependencies();
            return checker.doCheck(node);
        }
    }
}
