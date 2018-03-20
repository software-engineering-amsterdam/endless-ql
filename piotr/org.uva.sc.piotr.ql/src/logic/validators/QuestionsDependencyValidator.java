package logic.validators;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;
import exceptions.QuestionDependencyException;

import java.util.*;

public final class QuestionsDependencyValidator {

    private HashSet<Relation> relations = new HashSet<>();

    static class Relation {
        private final Question from;
        private final Question to;

        Relation(Question from, Question to) {
            this.from = from;
            this.to = to;
        }

        public boolean isReflexive() {
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

    public QuestionsDependencyValidator(HashMap<Question, List<VariableReference>> questionsMap) {
        this.buildRelationsSet(questionsMap);
        this.constructTransitiveClosure();

        for (Relation relation : this.relations) {
            if (relation.isReflexive()) {
                throw new QuestionDependencyException("Detected question dependency: variable \"" +
                        relation.from.getVariableName() +
                        "\". On line " +
                        relation.from.getMetaInformation().getStartLine() +
                        "."
                );
            }
        }
    }

    private void buildRelationsSet(HashMap<Question, List<VariableReference>> questionsMap) {
        // create edges
        for (Map.Entry<Question, List<VariableReference>> entry : questionsMap.entrySet()) {
            // find variables that it refers to
            for (VariableReference reference : entry.getValue()) {
                Question question = this.findQuestionByVariableName(reference.getName(), questionsMap.keySet());
                if (question != null) {
                    this.relations.add(new Relation(entry.getKey(), question));
                }
            }
        }
    }

    private Question findQuestionByVariableName(String variableName, Set<Question> questions) {
        for (Question question : questions) {
            if (question.getVariableName().equals(variableName)) {
                return question;
            }
        }
        return null;
    }

    private boolean relationExists(Question from, Question to) {
        for (Relation relation : this.relations) {
            if (relation.from.equals(from) && relation.to.equals(to)) {
                return true;
            }
        }

        return false;
    }

    private void constructTransitiveClosure() {

        boolean carryOn = true;

        while (carryOn) {
            carryOn = false;
            HashSet<Relation> closure = new HashSet<>();

            for (Relation r1 : this.relations) {
                for (Relation r2 : this.relations) {
                    if (r1.to.equals(r2.from)) {
                        if (!this.relationExists(r1.from, r2.to)) {
                            closure.add(new Relation(r1.from, r2.to));
                            carryOn = true;
                        }
                    }
                }
            }

            this.relations.addAll(closure);
        }
    }
}
