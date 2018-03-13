package qlviz.gui.renderer.javafx.widgets;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.*;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownUIWidget implements UIWidget, QuestionViewModelVisitor, ParameterVisitor {

    private final ComboBox<String> comboBox = new ComboBox<>();

    public DropdownUIWidget(List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            parameter.accept(this);
        }
    }

    @Override
    public Node getNode() {
        return this.comboBox;
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

    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {

    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {

    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {
        stringQuestion.valueProperty().bindBidirectional(this.comboBox.valueProperty());
    }

    @Override
    public void visit(StringParameter stringParameter) {
        this.comboBox.itemsProperty().get().add(stringParameter.getValue());
    }

    @Override
    public void visit(NumericParameter numericParameter) {

    }

    @Override
    public void visit(ColorParameter colorParameter) {

    }
}
