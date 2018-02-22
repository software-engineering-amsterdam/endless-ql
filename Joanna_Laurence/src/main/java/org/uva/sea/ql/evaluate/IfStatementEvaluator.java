package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLEvaluator;
import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;

import java.util.ArrayList;
import java.util.List;

public class IfStatementEvaluator extends QLValueEvaluator<List<Question>> {

    /**
     * Expression evaluator
     */
    private QLEvaluator qlEvaluator = new QLEvaluator();

    /**
     * List of questions that are seen
     */
    private List<Question> cachedQuestions = new ArrayList<>();

    /**
     * Evaluates the condition, when true the statements are returned
     * @param ifStatement Statement that is evaluated
     * @param symbolTable Symbol table with data
     * @return List of all seen questions
     */
    public List<Question> evaluate(IfStatement ifStatement, SymbolTable symbolTable) {

        //Get all questions inside the statements
        Value questions = this.qlEvaluator.evaluate(ifStatement.getStatements(), symbolTable);
        questions.accept(this);

        //Visit the condition that returns the list of questions when they should be visible
        Value condition = this.qlEvaluator.evaluate(ifStatement.getExpression(), symbolTable);
        return condition.accept(this);
    }

    public List<Question> visit(BooleanValue boolValue) {
        if(boolValue.getBooleanValue()) {
            return this.cachedQuestions;
        }

        return new ArrayList<>();
    }

    //TODO: Add this in another visit. Return type is not needed
    public List<Question> visit(QuestionValue questionValue) {
        cachedQuestions.add(questionValue.getQuestion());
        return null;
    }
}