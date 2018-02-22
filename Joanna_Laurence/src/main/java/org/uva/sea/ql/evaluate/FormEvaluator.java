package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.traverse.BaseVisitor;

import java.util.List;

public class FormEvaluator extends BaseVisitor<List<Question>> {

    /**
     * Expression evaluator
     */
    private IfStatementEvaluator ifStatementEvaluator = new IfStatementEvaluator();

    /**
     * List with all questions in the system
     */
    private SymbolTable symbolTable;

    /**
     * Evaluates the form
     * @param form Form that is evaluated
     * @param symbolTable Symbol table with data
     * @return List of all seen questions
     */
    public List<Question> evaluate(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return form.accept(this);
    }

    /**
     * Merge all questions from all statements
     * @param node Statement node
     * @return Questions
     */
    public List<Question> visit(Statements node) {
        for(ASTNode statement : node.getStatementList()) {
            statement.accept(this);
        }

        StatementsEvaluator statementsEvaluator = new StatementsEvaluator(node);
        return statementsEvaluator.evaluate();
    }


    /**
     * Use the if statement evaluator to get a list of questions from a if statement
     * @param ifStatement The if statement
     * @return Questions
     */
    public List<Question> visit(IfStatement ifStatement) {
        ifStatement.getStatements().accept(this);
        return this.ifStatementEvaluator.evaluate(ifStatement, this.symbolTable);
    }
}