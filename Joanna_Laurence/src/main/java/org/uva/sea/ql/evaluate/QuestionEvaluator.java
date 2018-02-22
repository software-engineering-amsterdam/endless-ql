package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionEvaluator extends QLValueEvaluator<List<Question>> {

    /**
     * Items that holds all the Questions inside the condition
     */
    private Value questionStatements;

    /**
     * List of questions that are seen
     */
    private List<Question> cachedQuestions = new ArrayList<>();

    /**
     * Evaluates the condition, when true the statements are returned
     * @param condition Value with True or False inside
     * @param questionStatements Items that contains the questions
     * @return List of all seen questions
     */
    public List<Question> evaluate(Value condition, Value questionStatements) {
        this.questionStatements = questionStatements;
        return condition.accept(this);
    }

    public List<Question> visit(BooleanValue boolValue) {

        if(boolValue.getBooleanValue()) {
            this.questionStatements.accept(this);
            return this.cachedQuestions;
        }

        return new ArrayList<>();
    }

    //Add rest with error

    public List<Question> visit(QuestionValue questionValue) {
        cachedQuestions.add(QuestionValue.getQuestion());
        return null;
    }
}