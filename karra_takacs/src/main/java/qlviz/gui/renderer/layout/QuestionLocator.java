package qlviz.gui.renderer.layout;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.*;

import java.util.Optional;
import java.util.Stack;

public interface QuestionLocator {
    Optional<Page> getPage(QuestionViewModel questionViewModel);
    Optional<Section> getSection(QuestionViewModel questionViewModel);
    QuestionLocation getPathToQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException;
}

