package ql.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class IdentifiersCollector {

    // Get all question identifiers of a form
    public static List<String> collectQuestionIdentifiers(Form form) {
        List<String> questionIdentifiers = new ArrayList<>();

        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                questionIdentifiers.add(question.identifier);
                return super.visit(question);
            }
        });

        return questionIdentifiers;
    }

    // Get all identifiers to which is referred in the form
    public static List<String> collectReferencedIdentifiers(Form form) {
        List<String> referencedIdentifiers = new ArrayList<>();

        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(ExpressionIdentifier expression) {
                referencedIdentifiers.add(expression.identifier);
                return super.visit(expression);
            }
        });

        return referencedIdentifiers;
    }

    // Get all identifiers to which is referred in an expression
    public static List<String> collectReferencedIdentifiers(Expression expression) {
        List<String> referencedIdentifiers = new ArrayList<>();

        expression.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(ExpressionIdentifier expression) {
                referencedIdentifiers.add(expression.identifier);
                return super.visit(expression);
            }
        });

        return referencedIdentifiers;
    }
}
