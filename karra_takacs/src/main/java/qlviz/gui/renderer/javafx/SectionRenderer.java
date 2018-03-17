package qlviz.gui.renderer.javafx;

import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.List;

public interface SectionRenderer {
    void render(List<QuestionViewModel> questionViewModels);
}
