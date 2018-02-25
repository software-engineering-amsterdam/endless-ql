package qlviz.gui.viewModel;

import qlviz.gui.renderer.FormRenderer;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface FormViewModel extends QuestionObserver {

    String getTitle();

    List<QuestionBlockViewModel> getQuestions();
}
