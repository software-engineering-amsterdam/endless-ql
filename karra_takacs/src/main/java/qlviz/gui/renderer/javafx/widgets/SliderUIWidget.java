package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import qlviz.gui.viewModel.question.*;

import java.math.BigDecimal;

public class SliderUIWidget implements UIWidget, QuestionViewModelVisitor {

    private final Slider slider = new Slider();

    @Override
    public Node getNode() {
        return this.slider;
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
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
                decimalQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
                integerQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
         slider.valueProperty().addListener((observable, oldValue, newValue) ->
                moneyQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {

    }
}

