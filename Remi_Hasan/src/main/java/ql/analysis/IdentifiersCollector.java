package ql.analysis;

import ql.model.Form;
import ql.model.expression.Expression;
import ql.model.expression.Identifier;
import ql.model.statement.Question;
import ql.visitor.QLVisitor;

import java.util.HashSet;
import java.util.Set;

public class IdentifiersCollector {

    // Get all question identifiers of a form
    public static Set<String> collectQuestionIdentifiers(Form form) {
        Set<String> questionIdentifiers = new HashSet<>();

        form.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                questionIdentifiers.add(question.getIdentifier());
                return super.visit(question);
            }
        });

        return questionIdentifiers;
    }

    // Get all identifiers to which is referred in the form
    public static Set<String> collectReferencedIdentifiers(Form form) {
        Set<String> referencedIdentifiers = new HashSet<>();

        form.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Identifier expression) {
                referencedIdentifiers.add(expression.getIdentifier());
                return super.visit(expression);
            }
        });

        return referencedIdentifiers;
    }

    // Get all identifiers to which is referred in an expression
    public static Set<String> collectReferencedIdentifiers(Expression expression) {
        Set<String> referencedIdentifiers = new HashSet<>();

        expression.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Identifier expression) {
                referencedIdentifiers.add(expression.getIdentifier());
                return super.visit(expression);
            }
        });

        return referencedIdentifiers;
    }
}
