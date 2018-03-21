package qlviz.gui.renderer.javafx;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Page;
import qlviz.model.style.Stylesheet;

import java.util.function.Function;

public class JavafxFormRenderer implements FormRenderer {

    protected final Stage stage;
    protected final Function<Pane, QuestionRenderer> questionRendererFactory;

    public JavafxFormRenderer(Stage stage, Function<Pane, QuestionRenderer> questionRendererFactory) {
        this.stage = stage;
        this.questionRendererFactory = questionRendererFactory;
    }

    @Override
    public void render(FormViewModel form) {
        stage.setTitle(form.getTitle());

        VBox formFieldsContainer = new VBox();

        QuestionRenderer questionRenderer = questionRendererFactory.apply(formFieldsContainer);
        for (QuestionViewModel question : form.getQuestions()) {
            questionRenderer.render(question);
        }
        Scene scene = new Scene(formFieldsContainer, 550, 250);
        stage.setScene(scene);

        stage.show();
    }
}

