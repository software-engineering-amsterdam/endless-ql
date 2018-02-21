package qlviz.gui.renderer;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;

import java.util.function.Function;

public class JavafxFormRenderer implements FormRenderer {

    private final Stage stage;
    private final Function<VBox, QuestionBlockRenderer> questionBlockRendererFactory;

    public JavafxFormRenderer(Stage stage, Function<VBox, QuestionBlockRenderer> questionBlockRendererFactory) {
        this.stage = stage;
        this.questionBlockRendererFactory = questionBlockRendererFactory;
    }

    @Override
    public void render(Form form) {
        stage.setTitle(form.getTitle());

        VBox formFieldsContainer = new VBox();

        QuestionBlockRenderer questionBlockRenderer = questionBlockRendererFactory.apply(formFieldsContainer);
        for (QuestionBlock questionBlock : form.getQuestions()) {
            questionBlockRenderer.render(questionBlock);
        }
        Scene scene = new Scene(formFieldsContainer, 550, 250);
        stage.setScene(scene);

        stage.show();
    }
}

