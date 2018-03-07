package qlviz.gui.renderer.javafx;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.function.Function;

public class JavafxFormRenderer implements FormRenderer {

    private final Stage stage;
    private final Function<VBox, QuestionRenderer> questionRendererFactory;

    public JavafxFormRenderer(Stage stage, Function<VBox, QuestionRenderer> questionRendererFactory) {
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

