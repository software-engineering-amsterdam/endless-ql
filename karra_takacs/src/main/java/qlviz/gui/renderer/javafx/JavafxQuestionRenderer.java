package qlviz.gui.renderer.javafx;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.model.question.*;

public class JavafxQuestionRenderer implements QuestionRenderer, QuestionVisitor {

    private final Pane target;

    public JavafxQuestionRenderer(Pane target) {
        this.target = target;
    }

    @Override
    public void render(Question question) {
        question.accept(this);
    }

    @Override
    public void visit(BooleanQuestion booleanQuestion) {

    }

    @Override
    public void visit(DateQuestion dateQuestion) {

    }

    @Override
    public void visit(DecimalQuestion decimalQuestion) {

    }

    @Override
    public void visit(IntegerQuestion integerQuestion) {

    }

    @Override
    public void visit(MoneyQuestion moneyQuestion) {

    }

    @Override
    public void visit(StringQuestion stringQuestion) {
        Label label = new Label(stringQuestion.getText());
        TextField textField = new TextField();
        textField.setMinWidth(50);
        textField.setPrefWidth(50);
        target.getChildren().add(label);
        target.getChildren().add(textField);

    }
}
