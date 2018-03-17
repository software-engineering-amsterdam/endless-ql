package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.PropertySetting;

import java.math.BigDecimal;

public class TextFieldUIWidget extends ControlUIWidget<TextField> implements UIWidget, QuestionViewModelVisitor {

    public TextFieldUIWidget() {
        super();
        this.node = new TextField();
    }

    @Override
    public void setProperty(PropertySetting setting) {
        super.setProperty(setting);
        ParameterValueReader parameterValueReader = new ParameterValueReader();
        setting.getValue().accept(parameterValueReader);
        switch (setting.getPropertyKey()) {
            case "font":
                this.node.setFont(new Font(parameterValueReader.getStringValue(), this.node.getFont().getSize()));
                break;
            case "fontsize":
                this.node.setFont(new Font(this.node.getFont().getName(), parameterValueReader.getNumericValue().doubleValue()));
                break;
        }
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
       this.node.textProperty().bindBidirectional(decimalQuestion.valueProperty(), new StringConverter<BigDecimal>() {
			@Override
			public String toString(BigDecimal object) {
				return object.toString();
			}

			@Override
			public BigDecimal fromString(String string) {
				try {
					return new BigDecimal(string);
				} catch (NumberFormatException e) {
                    node.setText("");
					return BigDecimal.ZERO;
				}
			}
		});
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
       this.node.textProperty().bindBidirectional(integerQuestion.valueProperty(), new StringConverter<BigDecimal>() {
			@Override
			public String toString(BigDecimal object) {
				return object.toString();
			}

			@Override
			public BigDecimal fromString(String string) {
				try {
					return new BigDecimal(string);
				} catch (NumberFormatException e) {
                    node.setText("");
					return BigDecimal.ZERO;
				}
			}
		});

    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
       this.node.textProperty().bindBidirectional(moneyQuestion.valueProperty(), new StringConverter<BigDecimal>() {
			@Override
			public String toString(BigDecimal object) {
				return object.toString();
			}

			@Override
			public BigDecimal fromString(String string) {
				try {
					return new BigDecimal(string);
				} catch (NumberFormatException e) {
                    node.setText("");
					return BigDecimal.ZERO;
				}
			}
		});

    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {
	this.node.textProperty().bindBidirectional(stringQuestion.valueProperty());
    }
}

