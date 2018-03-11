package qlviz.gui.renderer.javafx;

import javafx.scene.Node;
import qlviz.gui.viewModel.question.QuestionViewModel;

public interface UIWidget {
    public Node getNode();
    public void bindToQuestion(QuestionViewModel questionViewModel);
}
