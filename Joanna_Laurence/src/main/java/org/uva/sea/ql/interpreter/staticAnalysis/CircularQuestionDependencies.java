package org.uva.sea.ql.interpreter.staticAnalysis;

import org.uva.sea.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.types.Variable;
import org.uva.sea.ql.parser.visitor.BaseASTVisitor;

import java.util.*;

public class CircularQuestionDependencies extends BaseASTVisitor<Void> implements IStaticAnalysis {

    private Map<String,List<String>> dependencies = new HashMap<>();

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
        return incorrectDependenciesToErrors(this.isMapAsymmetric(this.dependencies));
    }

    /**
     * Convert dependency errors to Messages
     * @param incorrectDependencies The dependency errors
     * @return The messages
     */
    private Messages incorrectDependenciesToErrors(List<AbstractMap.SimpleEntry<String, String>> incorrectDependencies) {
        Messages errors = new Messages(MessageTypes.ERROR);
        for( AbstractMap.SimpleEntry<String,String> dependency : incorrectDependencies)
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

        listsToMap(questions, dependsOn, this.dependencies);

        super.visit(node);
        return null;
    }

    /**
     * Combines two lists into a map. List one is related with all of list two
     * @param firstList First list
     * @param secondList Second list
     * @param map The output
     * @param <T> Type
     */
    private <T> void listsToMap(final Collection<T> firstList, final List<T> secondList, Map<T,List<T>> map) {
        for(T first : firstList) {
            List<T> keyContent = map.get(first);
            if(keyContent == null) {
                map.put(first, secondList);
            } else {
                keyContent.addAll(secondList);
            }
        }
    }

    /**
     * for all a,b IN X (aRb -> NOT(bRa)).
     * Return all elements that make the map not asymmetric
     * @param map The map with relations
     * @param <T> Type
     * @return Relations that make the map not asymmetric
     */
    private <T> List<AbstractMap.SimpleEntry<T,T>> isMapAsymmetric(Map<T,List<T>> map) {
        List<AbstractMap.SimpleEntry<T,T>> incorrectElements = new ArrayList<>();

        for (Map.Entry<T, List<T>> entry : map.entrySet())
        {
            T key = entry.getKey();
            for(T element : entry.getValue()) {
                List<T> oppositeRelations = map.get(element);
                if(oppositeRelations != null && oppositeRelations.contains(key))
                    incorrectElements.add(new AbstractMap.SimpleEntry<>(key, element));
            }
        }
        return incorrectElements;
    }
}
