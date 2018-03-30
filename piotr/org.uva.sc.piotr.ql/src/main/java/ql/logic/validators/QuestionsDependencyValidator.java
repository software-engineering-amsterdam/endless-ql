package ql.logic.validators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.repositories.QuestionRepository;

import java.util.*;

public final class QuestionsDependencyValidator extends Validator {

    private final HashMap<Question, List<VariableReference>> questionsMap;
    private final List<Question> questions;

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
            if (obj != null && obj instanceof Relation) {
                Relation relation = (Relation) obj;
                return relation.from == this.from && relation.to == this.to;
            }
            return false;
        }

        @Override
        public String toString() {
            return from.getVariableName() + " -> " + to.getVariableName();
        }
    }

    public QuestionsDependencyValidator(HashMap<Question, List<VariableReference>> questionsMap) {
        this.questionsMap = questionsMap;
        this.questions = new ArrayList<>(questionsMap.keySet());
    }

    public boolean validate() {

        HashSet<Relation> closure = transitiveClosure((buildRelationsSet()));

        for (Relation relation : closure) {
            if (relation.isReflexive()) {
                String message = "Detected cyclic question dependency: variable \"" +
                        relation.from.getVariableName() +
                        "\". On line " +
                        relation.from.getMetaInformation().getStartLine() +
                        ".";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }
        return true;
    }

    private HashSet<Relation> buildRelationsSet() {
        HashSet<Relation> relations = new HashSet<>();
        // create edges
        for (Map.Entry<Question, List<VariableReference>> entry : this.questionsMap.entrySet()) {
            // find variables that it refers to
            for (VariableReference reference : entry.getValue()) {
                Question question = QuestionRepository.findQuestionByVariableName(reference.getName(), this.questions);
                if (question != null) {
                    relations.add(new Relation(entry.getKey(), question));
                }
            }
        }
        return relations;
    }

    private boolean relationExists(Question from, Question to, HashSet<Relation> relations) {
        for (Relation relation : relations) {
            if (relation.from.equals(from) && relation.to.equals(to)) {
                return true;
            }
        }
        return false;
    }

    private HashSet<Relation> transitiveClosure(HashSet<Relation> relations) {

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
