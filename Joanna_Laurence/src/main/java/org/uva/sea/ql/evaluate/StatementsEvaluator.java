package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.Statements;
import org.uva.sea.ql.traverse.BaseVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatementsEvaluator extends BaseVisitor<List<Question>> {

    /**
     *
     */
    private SymbolTable symbolTable;

    /**
     *
     */
    private IfStatementEvaluator ifStatementEvaluator = new IfStatementEvaluator();

    /**
     *
     * @param symbolTable
     */
    public StatementsEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * Evaluates the statements
     * @return List of all seen questions
     */
    public List<Question> evaluate(Statements statements) {
        return statements.accept(this);
    }

    /**
     * Merge all questions from all statements
     * @param node Statement node
     * @return Questions
     */
    public List<Question> visit(Statements node) {
        List<Question> questions = new ArrayList<>();
        for(ASTNode statement : node.getStatementList()) {
            List<Question> subQuestions = statement.accept(this);
            if(subQuestions != null)
                questions.addAll(subQuestions);
        }
        return questions;
    }

    /**
     * Extract questions for AST
     * @param question The questions in the AST
     * @return The questions in a list
     */
    public List<Question> visit(Question question) {
        return Arrays.asList(new Question[] {question});
    }

    /**
     * Do not evaluate if statements
     * @param ifStatement
     * @return
     */
    public List<Question> visit(IfStatement ifStatement) {
        return this.ifStatementEvaluator.evaluate(ifStatement, this.symbolTable);
    }
}