package qlviz.gui.viewModel.question;

import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.question.Question;

import java.util.List;

public interface QuestionViewModelFactory  {
    QuestionViewModel create(Question model);
}
