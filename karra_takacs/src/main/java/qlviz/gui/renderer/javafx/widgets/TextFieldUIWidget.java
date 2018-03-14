package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import qlviz.gui.viewModel.question.*;

import java.math.BigDecimal;

public class TextFieldUIWidget implements UIWidget, QuestionViewModelVisitor {

    private final TextField textField = new TextField();

    @Override
    public Node getNode() {
        return this.textField;
    }

    @Override
    public void bindToQuestion(QuestionViewModel questionViewModel) {
        questionViewModel.accept(this);
    }

    @Override
    public void visit(BooleanQuestionViewModel booleanQuestion) {

    }

    @Override
    public void visit(DateQuestionViewModel dateQuestion) {

    }

    @Override
    public void visit(DecimalQuestionViewModel decimalQuestion) {
        textField.textProperty().bindBidirectional(decimalQuestion.valueProperty(), new StringConverter<BigDecimal>() {
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
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
        textField.textProperty().bindBidirectional(integerQuestion.valueProperty(), new StringConverter<BigDecimal>() {
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

    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
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

    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {

    }
}

