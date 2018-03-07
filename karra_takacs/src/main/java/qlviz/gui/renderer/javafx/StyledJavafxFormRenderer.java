package qlviz.gui.renderer.javafx;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.model.style.Page;
import qlviz.model.style.Stylesheet;

import java.util.function.Function;

public class StyledJavafxFormRenderer extends JavafxFormRenderer {

    private final Stylesheet stylesheet;

    public StyledJavafxFormRenderer(Stage stage, Function<VBox, QuestionRenderer> questionRendererFactory, Stylesheet stylesheet) {
        super(stage, questionRendererFactory);
        this.stylesheet = stylesheet;
    }

    private void render(TabPane target, Page page, FormViewModel form) {
        Tab tab = new Tab();
        tab.setText(page.getName());


        target.getTabs().add(tab);
    }

    @Override
    public void render(FormViewModel form) {
        stage.setTitle(form.getTitle());

        TabPane formFieldsContainer = new TabPane();

        for (Page page : stylesheet.getPages()) {
            this.render(formFieldsContainer, page, form);
        }

        Scene scene = new Scene(formFieldsContainer, 550, 250);
        stage.setScene(scene);

        stage.show();
    }
}
