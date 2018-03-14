package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Widget;

public interface WidgetFinder {
    Widget findWidgetForQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException, WidgetNotFoundException;
}
