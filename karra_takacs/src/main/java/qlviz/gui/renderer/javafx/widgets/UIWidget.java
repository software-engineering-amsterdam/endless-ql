package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Control;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.PropertySetting;

public interface UIWidget {
    Node getNode();
    void bindToQuestion(QuestionViewModel questionViewModel);
    void setProperty(PropertySetting setting);
}

