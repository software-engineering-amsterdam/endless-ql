package qlviz.gui.viewModel.question;

import qlviz.model.question.Question;

public interface QuestionViewModelFactory  {
    QuestionViewModel create(Question model);
}
