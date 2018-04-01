package qlviz.gui.renderer.javafx;

import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Section;

import java.util.List;

public interface StyledSectionRenderer {
    void render(List<QuestionViewModel> questionViewModels, Section section);
}
