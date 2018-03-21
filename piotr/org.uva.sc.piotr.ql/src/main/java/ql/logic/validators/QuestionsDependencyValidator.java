package ql.logic.validators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.exceptions.QuestionDependencyException;

import java.util.*;

public final class QuestionsDependencyValidator {

    static class Relation {
        private final Question from;
        private final Question to;

        Relation(Question from, Question to) {
            this.from = from;
            this.to = to;
        }

        boolean isReflexive() {
            return this.from.equals(this.to);
        }

        @Override
        public boolean equals(Object obj) {
            Relation relation = (Relation) obj;
            return relation.from == this.from && relation.to == this.to;
        }

        @Override
        public String toString() {
            return from.getVariableName() + " -> " + to.getVariableName();
        }
    }

    public static void validateCyclicDependencies(HashMap<Question, List<VariableReference>> questionsMap) {

        HashSet<Relation> closure = transitiveClosure((buildRelationsSet(questionsMap)));

        for (Relation relation : closure) {
            if (relation.isReflexive()) {
                throw new QuestionDependencyException("Detected cyclic question dependency: variable \"" +
                        relation.from.getVariableName() +
                        "\". On line " +
                        relation.from.getMetaInformation().getStartLine() +
                        "."
                );
            }
        }
    }

    private static HashSet<Relation> buildRelationsSet(HashMap<Question, List<VariableReference>> questionsMap) {
        HashSet<Relation> relations = new HashSet<>();
        // create edges
        for (Map.Entry<Question, List<VariableReference>> entry : questionsMap.entrySet()) {
            // find variables that it refers to
            for (VariableReference reference : entry.getValue()) {
                Question question = findQuestionByVariableName(reference.getName(), questionsMap.keySet());
                if (question != null) {
                    relations.add(new Relation(entry.getKey(), question));
                }
            }
        }
        return relations;
    }

    private static Question findQuestionByVariableName(String variableName, Set<Question> questions) {
        for (Question question : questions) {
            if (question.getVariableName().equals(variableName)) {
                return question;
            }
        }
        return null;
    }

    private static boolean relationExists(Question from, Question to, HashSet<Relation> relations) {
        for (Relation relation : relations) {
            if (relation.from.equals(from) && relation.to.equals(to)) {
                return true;
            }
        }
        return false;
    }

    private static HashSet<Relation> transitiveClosure(HashSet<Relation> relations) {

        boolean carryOn = true;
        HashSet<Relation> closure = new HashSet<>(relations);

        while (carryOn) {
            carryOn = false;
            HashSet<Relation> subClosure = new HashSet<>();

            for (Relation r1 : closure) {
                for (Relation r2 : closure) {
                    if (r1.to.equals(r2.from)) {
                        if (!relationExists(r1.from, r2.to, closure)) {
                            subClosure.add(new Relation(r1.from, r2.to));
                            carryOn = true;
                        }
                    }
                }
            }
            closure.addAll(subClosure);
        }
        return closure;
    }
}
