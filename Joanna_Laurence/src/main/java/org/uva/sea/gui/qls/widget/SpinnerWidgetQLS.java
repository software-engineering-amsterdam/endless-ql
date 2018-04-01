package org.uva.sea.gui.qls.widget;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class SpinnerWidgetQLS extends WidgetQLS {

    private static final double SPINNER_MAX_VALUE = 100;

    private Value widgetValue = new DecimalValue(0);

    public SpinnerWidgetQLS(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DecimalValue decimalValue) {
        this.widgetValue = decimalValue;
        return true;
    }

    @Override
    public boolean updateValue(IntValue intValue) {
        this.widgetValue = intValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        if (this.widgetValue == null)
            return null;

        Spinner spinner = new Spinner<>();

        this.setStyle(spinner);

        this.widgetValue.accept(new BaseValueVisitor<Void>() {
            @Override
            public Void visit(IntValue node) {
                SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (int) SpinnerWidgetQLS.SPINNER_MAX_VALUE, node.getIntValue());
                spinner.setValueFactory(valueFactory);
                return null;
            }

            @Override
            public Void visit(DecimalValue node) {
                SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, SpinnerWidgetQLS.SPINNER_MAX_VALUE, node.getDecimalValue());
                spinner.setValueFactory(valueFactory);
                return null;
            }
        });

        spinner.valueProperty().addListener(
                (ChangeListener<Number>) (observableValue, oldValue, newValue) -> {

                    Value newWidgetValue = this.widgetValue.accept(new BaseValueVisitor<Value>() {
                        @Override
                        public Value visit(DecimalValue node) {
                            return new DecimalValue(newValue.toString());
                        }

                        @Override
                        public Value visit(IntValue node) {
                            return new IntValue(newValue.toString());
                        }
                    });

                    this.sendUpdateValueEvent(this.questionData.getQuestionName(), newWidgetValue);
                });


        return spinner;
    }
}
