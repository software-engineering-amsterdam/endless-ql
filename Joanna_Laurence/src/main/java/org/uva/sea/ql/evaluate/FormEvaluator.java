package org.uva.sea.ql.evaluate;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.uva.sea.ql.QLEvaluator;
import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.traverse.BaseVisitor;
import xtc.tree.Visitor;

import java.util.ArrayList;
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
        List<Question> questions = new ArrayList<>();
        for(ASTNode statement : node.getStatementList()) {
            questions.addAll(statement.accept(this));
        }
        return questions;
    }

    /**
     * Questions as an array
     * @param question The questions
     * @return List of questions
     */
    public List<Question> visit(Question question) {;
        return Arrays.asList(new Question[] {question});
    }

    /**
     * Use the if statement evaluator to get a list of questions from a if statement
     * @param ifStatement The if statement
     * @return Questions
     */
    public List<Question> visit(IfStatement ifStatement) {
        return this.ifStatementEvaluator.evaluate(ifStatement, this.symbolTable);
    }
}