package org.uva.sea.gui.qls.widget;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.uva.sea.gui.widget.BaseRenderable;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class SpinnerWidget extends QLSWidget {

    private static final double DECIMAL_STEP_SIZE = 0.1;

    private static final int INTEGER_STEP_SIZE = 1;

    private static final double SPINNER_MAX_VALUE = 100;

    private Value widgetValue = new DecimalValue(0);

    private Value incrementStep = new DecimalValue(DECIMAL_STEP_SIZE);

    public SpinnerWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DecimalValue decimalValue) {
        this.widgetValue = decimalValue;
        this.incrementStep = new DecimalValue(DECIMAL_STEP_SIZE);
        return true;
    }

    @Override
    public boolean updateValue(IntValue intValue) {
        this.widgetValue = intValue;
        this.incrementStep = new IntValue(INTEGER_STEP_SIZE);
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        if (this.widgetValue == null)
            return null;

        final Spinner spinner = new Spinner<>();
        spinner.setFocusTraversable(false);

        this.addSpinnerStyle(spinner, this.questionData.getStyle());

        this.widgetValue.accept(new BaseValueVisitor<Void>() {
            @Override
            public Void visit(IntValue node) {
                SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (int) SPINNER_MAX_VALUE, node.getIntValue());
                spinner.setValueFactory(valueFactory);
                return null;
            }

            @Override
            public Void visit(DecimalValue node) {
                SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, SPINNER_MAX_VALUE, node.getDecimalValue());
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

    private void addSpinnerStyle(Spinner spinner, Style style) {
        if (style == null)
            return;

        if (style.getWidth() != null) {
            spinner.setMinWidth(style.getWidth());
        } else {
            spinner.setMinWidth(BaseRenderable.TEXT_WIDTH); //default style
        }
    }
}
