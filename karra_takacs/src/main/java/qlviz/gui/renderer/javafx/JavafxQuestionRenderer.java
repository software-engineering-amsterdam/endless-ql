package qlviz.gui.renderer.javafx;

import javafx.event.EventType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.question.*;
import qlviz.model.question.*;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class JavafxQuestionRenderer implements QuestionRenderer, QuestionViewModelVisitor {

	private final Pane target;

	public JavafxQuestionRenderer(Pane target) {
		this.target = target;
	}

	@Override
	public void visit(BooleanQuestionViewModel booleanQuestion) {
		VBox container = new VBox();

		Label label = new Label(booleanQuestion.getText());
		CheckBox checkBox = new CheckBox();
		checkBox.selectedProperty().bindBidirectional(booleanQuestion.valueProperty());

		container.getChildren().add(label);
		container.getChildren().add(checkBox);
		container.visibleProperty().bind(booleanQuestion.isEnabledProperty());
		target.getChildren().add(container);

	}

	@Override
	public void visit(DateQuestionViewModel dateQuestion) {

	}

	@Override
	public void visit(DecimalQuestionViewModel decimalQuestion) {

	}

	@Override
	public void visit(IntegerQuestionViewModel integerQuestion) {

	}

	@Override
	public void visit(MoneyQuestionViewModel moneyQuestion) {
		VBox container = new VBox();
		Label label = new Label(moneyQuestion.getText());
		TextField textField = new TextField();
		textField.setMinWidth(50);
		textField.setPrefWidth(50);
		textField.visibleProperty().bind(moneyQuestion.isEnabledProperty());
		textField.textProperty().bindBidirectional(moneyQuestion.valueProperty(), new StringConverter<BigDecimal>() {
			@Override
			public String toString(BigDecimal object) {
				return object.toString();
			}

			@Override
			public BigDecimal fromString(String string) {
				try {
					return new BigDecimal(string);
				} catch (NumberFormatException e) {
					textField.setText("");
					return BigDecimal.ZERO;
				}
			}
		});

        container.getChildren().add(label);
		container.getChildren().add(textField);
		container.visibleProperty().bind(moneyQuestion.isEnabledProperty());
		target.getChildren().add(container);
	}

	@Override
	public void visit(StringQuestionViewModel stringQuestion) {
		VBox container = new VBox();
		Label label = new Label(stringQuestion.getText());
		TextField textField = new TextField();
		textField.setMinWidth(50);
		textField.setPrefWidth(50);
		textField.visibleProperty().bind(stringQuestion.isEnabledProperty());

		container.getChildren().add(label);
		container.getChildren().add(textField);
		container.visibleProperty().bind(stringQuestion.isEnabledProperty());
		target.getChildren().add(container);
	}

	@Override
	public void render(QuestionViewModel question) {
		question.accept(this);
	}
}
