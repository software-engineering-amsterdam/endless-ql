package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.PropertySetting;
import qlviz.model.style.Widget;

import java.util.List;

public interface WidgetFinder {
    Widget findWidgetForQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException, WidgetNotFoundException;
    List<PropertySetting> findDefaultProperties(QuestionViewModel questionViewModel) throws QuestionNotFoundException;
}
