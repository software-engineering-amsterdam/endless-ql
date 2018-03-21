package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.PropertySetting;

import java.math.BigDecimal;

public class SpinboxUIWidget extends ControlUIWidget<Spinner<BigDecimal>> implements QuestionViewModelVisitor {

    private class DecimalSpinnerValueFactory extends SpinnerValueFactory<BigDecimal> {

        public DecimalSpinnerValueFactory() {
            this.setValue(BigDecimal.ZERO);
        }

        @Override
        public void decrement(int steps) {
            this.valueProperty().setValue(
                this.valueProperty().get().subtract(BigDecimal.valueOf(steps)));
        }

        @Override
        public void increment(int steps) {
            this.valueProperty().setValue(
                    this.valueProperty().get().add(BigDecimal.valueOf(steps)));
        }
    }


    public SpinboxUIWidget() {
        DecimalSpinnerValueFactory factory = new DecimalSpinnerValueFactory();
        this.node = new Spinner<>();
        TextFormatter<BigDecimal> formatter = new TextFormatter<BigDecimal>(new BigDecimalStringConverter());
        factory.valueProperty().bindBidirectional(formatter.valueProperty());

        this.node.editableProperty().setValue(true);
        this.node.editorProperty().get().setTextFormatter(formatter);
        this.node.setValueFactory(factory);
    }

    @Override
    public void setProperty(PropertySetting setting) {
        super.setProperty(setting);
        ParameterValueReader parameterValueReader = new ParameterValueReader();
        setting.getValue().accept(parameterValueReader);
        switch (setting.getPropertyKey()) {
            case "color":
                this.node.getEditor().setStyle("-fx-text-fill: rgb(" +
                        parameterValueReader.getColorValue().getRed() + ", " +
                        parameterValueReader.getColorValue().getGreen() + ", " +
                        parameterValueReader.getColorValue().getBlue() + ");"
                );
                break;
        }
    }

    @Override
    public void bindToQuestion(QuestionViewModel questionViewModel) {
        questionViewModel.accept(this);
    }

    @Override
    public void visit(BooleanQuestionViewModel booleanQuestion) {
        return;
    }

    @Override
    public void visit(DateQuestionViewModel dateQuestion) {
        return;
    }

    private void visitInternal(NumericQuestionViewModel numericQuestion) {
        if (numericQuestion.getValueExpression() == null){
            numericQuestion.valueProperty().bind(this.node.getValueFactory().valueProperty());
        }
        else {
            this.node.editableProperty().setValue(false);
            numericQuestion.getValueExpression().valueProperty().addListener((observable, oldValue, newValue) ->
                this.node.getValueFactory().setValue(newValue));
        }
    }

    @Override
    public void visit(DecimalQuestionViewModel decimalQuestion) {
       this.visitInternal(decimalQuestion);
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
        this.visitInternal(integerQuestion);
    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
        this.visitInternal(moneyQuestion);
    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {
        return;
    }
}
