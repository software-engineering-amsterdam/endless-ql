package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Relation;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.RelationEntity;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.IfStatement;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.types.Variable;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.Collection;

public class CircularQuestionDependencies extends BaseASTVisitor<Void> implements IQLStaticAnalysis {

    private final Relation<String> dependencies = new Relation<>();

    /**
     * Hide constructor
     */
    private CircularQuestionDependencies() {

    }

    @Override
    public Messages doCheck(Form node) {
        node.accept(this);
        return this.incorrectDependenciesToErrors(this.getIncorrectAsymmetricElements(this.dependencies));
    }

    /**
     * Convert dependency errors to Messages
     *
     * @param incorrectDependencies The dependency errors
     * @return The messages
     */
    private Messages incorrectDependenciesToErrors(Relation<String> incorrectDependencies) {
        Messages errors = new Messages();
        for (RelationEntity<String> dependency : incorrectDependencies.getRelations())
            errors.addMessage(dependency.getKey() + " has a circular dependency with" + dependency.getValue(), MessageTypes.ERROR);
        return errors;
    }

    @Override
    public Void visit(IfStatement ifNode) {
        Collection<String> dependsOn = new ArrayList<>();
        ifNode.getExpression().accept(new BaseASTVisitor<Void>() {
            public Void visit(Variable node) {
                if ((node.getLinkedQuestion() != null) && (node.getLinkedQuestion().getValue() == null))
                    dependsOn.add(node.getVariableName());

                return super.visit(node);
            }
        });

        Collection<String> questions = new ArrayList<>();
        ifNode.getThen().accept(new BaseASTVisitor<Void>() {
            public Void visit(Question node) {
                questions.add(node.getVariable().getVariableName());
                return null;
            }
        });


        this.addRelations(questions, dependsOn, this.dependencies);

        super.visit(ifNode);
        return null;
    }

    /**
     * @param questions
     * @param dependsOn
     * @param relation
     */
    private void addRelations(Iterable<String> questions, Iterable<String> dependsOn, Relation<String> relation) {
        for (String question : questions) {
            for (String dependOn : dependsOn) {
                relation.addRelation(question, dependOn);
            }
        }
    }

    /**
     * for all a,b IN X (aRb -> NOT(bRa)).
     * Return all elements that make the map not asymmetric
     *
     * @param relation The relations
     * @return Relations that make the map not asymmetric
     */
    private Relation<String> getIncorrectAsymmetricElements(Relation<String> relation) {
        Relation<String> incorrectElements = new Relation<>();

        for (RelationEntity<String> entry : relation.getRelations()) {
            if (relation.contains(entry.getValue(), entry.getKey()))
                incorrectElements.addRelation(entry.getKey(), entry.getValue());
        }

        return incorrectElements;
    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IQLStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IQLStaticAnalysis checker = new CircularQuestionDependencies();
            return checker.doCheck(node);
        }
    }
}
