package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.Statements;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.List;

public class FormEvaluator extends BaseASTVisitor<List<Question>> {

    public final List<Question> evaluate(final Form form, final SymbolTable symbolTable) {
        final StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);

        final Statements formStatements = form.getStatements();
        return statementsEvaluator.evaluate(formStatements);
    }
}