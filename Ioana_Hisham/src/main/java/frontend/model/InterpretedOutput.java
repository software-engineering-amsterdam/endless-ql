package frontend.model;


import ql.ast.statements.Question;

import java.util.List;

/**
 * Created by Toshiba on 01/04/2018.
 */
public class InterpretedOutput {

    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "InterpretedOutput{" +
                "questions=" + questions +
                '}';
    }
}
