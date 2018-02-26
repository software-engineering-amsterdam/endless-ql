package qlviz.interpreter;

import qlviz.model.Form;
import qlviz.model.NumericQuestion;
import qlviz.model.question.BooleanQuestion;

import java.util.List;

public interface TypedQuestionCollector {
    public List<NumericQuestion> collectNumericQuestions(Form form);
    public List<BooleanQuestion> collectBooleanQuestions(Form form);
}
