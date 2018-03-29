package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import qlviz.gui.renderer.QuestionRenderer;

public interface QuestionRendererFactory {
    QuestionRenderer create(Pane pane);
}
