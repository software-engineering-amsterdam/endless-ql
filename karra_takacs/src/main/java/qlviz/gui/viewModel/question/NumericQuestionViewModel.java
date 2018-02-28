package qlviz.gui.viewModel.question;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import qlviz.model.question.NumericQuestion;

import java.math.BigDecimal;

public abstract class NumericQuestionViewModel extends BaseQuestionViewModel {

    private final NumericQuestion question;
    private final Property<BigDecimal> value;

    protected NumericQuestionViewModel(NumericQuestion question) {
        super(question);
        this.question = question;
        this.value = new SimpleObjectProperty<>(question.getValue());
        this.value.addListener((observable, oldValue, newValue) -> this.question.setValue(newValue));
    }

    public Property<BigDecimal> valueProperty() {
        return this.value;
    }
}
