package qlviz.gui.renderer;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.model.Form;

public interface FormRenderer {
    void render(FormViewModel form);
}

