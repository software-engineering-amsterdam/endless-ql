package qlviz.gui.renderer.layout;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Page;
import qlviz.model.style.Section;
import qlviz.model.style.Stylesheet;

import java.util.Optional;

public interface QuestionLocator {
    Optional<Page> getPage(QuestionViewModel questionViewModel);
    Optional<Section> getSection(QuestionViewModel questionViewModel);
}

