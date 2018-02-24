package qlviz.gui.renderer.javafx;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.model.question.Question;

public class JavafxQuestionRenderer implements QuestionRenderer {

    private final Pane target;

    public JavafxQuestionRenderer(Pane target) {
        this.target = target;
    }

    @Override
    public void render(Question question) {
        Label label = new Label(question.getText());
        TextField textField = new TextField();
        textField.setMinWidth(50);
        textField.setPrefWidth(50);
        target.getChildren().add(label);
        target.getChildren().add(textField);
    }
}
