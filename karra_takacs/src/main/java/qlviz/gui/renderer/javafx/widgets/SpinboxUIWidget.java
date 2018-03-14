package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;
import qlviz.gui.viewModel.question.*;

import java.math.BigDecimal;

public class SpinboxUIWidget implements UIWidget, QuestionViewModelVisitor {

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

    private final Spinner<BigDecimal> spinner;

    public SpinboxUIWidget() {
        DecimalSpinnerValueFactory factory = new DecimalSpinnerValueFactory();
        this.spinner = new Spinner<>();
        TextFormatter<BigDecimal> formatter = new TextFormatter<BigDecimal>(new BigDecimalStringConverter());
        factory.valueProperty().bindBidirectional(formatter.valueProperty());

        this.spinner.editableProperty().setValue(true);
        this.spinner.editorProperty().get().setTextFormatter(formatter);
        this.spinner.setValueFactory(factory);
    }

    @Override
    public Node getNode() {
        return spinner;
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
            numericQuestion.valueProperty().bind(this.spinner.getValueFactory().valueProperty());
        }
        else {
            this.spinner.editableProperty().setValue(false);
            this.spinner.getValueFactory().valueProperty().bind(numericQuestion.getValueExpression().valueProperty());
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
