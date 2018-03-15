package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.Statements;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.List;

public class FormEvaluator extends BaseASTVisitor<List<Question>> {

    /**
     * Evaluates the form
     *
     * @param form        Form that is evaluated
     * @param symbolTable Symbol table with helpers
     * @return List of all seen questions
     */
    public List<Question> evaluate(Form form, SymbolTable symbolTable) {
        StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);

        Statements formStatements = form.getStatements();
        return statementsEvaluator.evaluate(formStatements);
    }
}