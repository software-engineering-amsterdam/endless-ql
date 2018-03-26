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

    public SpinnerWidgetQLS(final QuestionData questionData) {
        super(questionData);
    }

    @Override
    public final boolean updateValue(final DecimalValue decimalValue) {
        this.widgetValue = decimalValue;
        return true;
    }

    @Override
    public final boolean updateValue(final IntValue intValue) {
        this.widgetValue = intValue;
        return true;
    }

    @Override
    public final Node convertToGuiNode() {
        if (this.widgetValue == null)
            return null;

        final Spinner spinner = new Spinner<>();

        this.setStyle(spinner);

        this.widgetValue.accept(new BaseValueVisitor<Void>() {
            @Override
            public Void visit(final IntValue node) {
                final SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (int) SpinnerWidgetQLS.SPINNER_MAX_VALUE, node.getIntValue());
                spinner.setValueFactory(valueFactory);
                return null;
            }

            @Override
            public Void visit(final DecimalValue node) {
                final SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, SpinnerWidgetQLS.SPINNER_MAX_VALUE, node.getDecimalValue());
                spinner.setValueFactory(valueFactory);
                return null;
            }
        });

        spinner.valueProperty().addListener(
                (ChangeListener<Number>) (observableValue, oldValue, newValue) -> {

                    final Value newWidgetValue = this.widgetValue.accept(new BaseValueVisitor<Value>() {
                        @Override
                        public Value visit(final DecimalValue node) {
                            return new DecimalValue(newValue.toString());
                        }

                        @Override
                        public Value visit(final IntValue node) {
                            return new IntValue(newValue.toString());
                        }
                    });

                    this.sendUpdateValueEvent(this.questionData.getQuestionName(), newWidgetValue);
                });


        return spinner;
    }
}
