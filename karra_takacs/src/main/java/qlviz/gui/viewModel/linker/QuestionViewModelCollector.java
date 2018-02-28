package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.question.BooleanQuestionViewModel;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;

import java.util.List;

public interface QuestionViewModelCollector {
    public List<NumericQuestionViewModel> collectNumericQuestions(FormViewModel form);
    public List<BooleanQuestionViewModel> collectBooleanQuestions(FormViewModel form);
}
