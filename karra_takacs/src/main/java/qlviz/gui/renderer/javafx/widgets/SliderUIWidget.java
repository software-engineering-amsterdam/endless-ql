package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import qlviz.gui.viewModel.question.*;

import java.math.BigDecimal;

public class SliderUIWidget extends ControlUIWidget<Slider> implements QuestionViewModelVisitor {

    public SliderUIWidget() {
        super();
        this.node = new Slider();
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
        this.node.valueProperty().addListener((observable, oldValue, newValue) ->
                decimalQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
        this.node.valueProperty().addListener((observable, oldValue, newValue) ->
                integerQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
        this.node.valueProperty().addListener((observable, oldValue, newValue) ->
                moneyQuestion.valueProperty().setValue(BigDecimal.valueOf(newValue.doubleValue())));
    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {

    }
}

