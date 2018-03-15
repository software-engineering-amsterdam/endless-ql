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

import java.util.*;

public class CircularQuestionDependencies extends BaseASTVisitor<Void> implements IStaticAnalysis {

    private Relation<String> dependencies = new Relation<>();

    /**
     * Hide constructor
     */
    private CircularQuestionDependencies() {

    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis checker = new CircularQuestionDependencies();
            return checker.doCheck(node);
        }
    }

    @Override
    public Messages doCheck(Form node) {
        node.accept(this);
        return incorrectDependenciesToErrors(this.getIncorrectAsymmetricElements(this.dependencies));
    }

    /**
     * Convert dependency errors to Messages
     * @param incorrectDependencies The dependency errors
     * @return The messages
     */
    private Messages incorrectDependenciesToErrors(Relation<String> incorrectDependencies) {
        Messages errors = new Messages(MessageTypes.ERROR);
        for( RelationEntity<String> dependency : incorrectDependencies.getRelations())
            errors.addMessage(dependency.getKey() + " has a circular dependency with" + dependency.getValue());
        return errors;
    }

    @Override
    public Void visit(IfStatement node) {
        List<String> dependsOn = new ArrayList<>();
        node.getExpression().accept(new BaseASTVisitor<Void>() {
            public Void visit(Variable node) {
                if(node.getLinkedQuestion() != null && node.getLinkedQuestion().getValue() == null)
                    dependsOn.add(node.getVariableName());

                return super.visit(node);
            }
        });

        List<String> questions = new ArrayList<>();
        node.getThen().accept(new BaseASTVisitor<Void>() {
            public Void visit(Question node) {
                questions.add(node.getVariable().getVariableName());
                return null;
            }
        });


        addRelations(questions, dependsOn, this.dependencies);

        super.visit(node);
        return null;
    }

    /**
     *
     * @param questions
     * @param dependsOn
     * @param relation
     */
    private void addRelations(List<String> questions, List<String> dependsOn, Relation<String> relation) {
        for(String question : questions) {
            for(String dependOn : dependsOn) {
                relation.addRelation(question, dependOn);
            }
        }
    }


    /**
     * for all a,b IN X (aRb -> NOT(bRa)).
     * Return all elements that make the map not asymmetric
     * @param relation The relations
     * @return Relations that make the map not asymmetric
     */
    private Relation<String> getIncorrectAsymmetricElements(Relation<String> relation) {
        Relation<String> incorrectElements = new Relation<>();

        for (RelationEntity<String> entry : relation.getRelations())
        {
            if(relation.contains(entry.getValue(),entry.getKey()))
                incorrectElements.addRelation(entry.getKey(), entry.getValue());
        }

        return incorrectElements;
    }
}
