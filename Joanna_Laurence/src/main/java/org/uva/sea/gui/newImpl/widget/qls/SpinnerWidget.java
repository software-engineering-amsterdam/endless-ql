package org.uva.sea.gui.newImpl.widget.qls;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.uva.sea.gui.newImpl.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.UndefinedValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class SpinnerWidget extends Widget {

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
    public Control convertToGuiNode() {
        if(this.widgetValue == null)
            return null;

        final Spinner spinner = new Spinner<>();

        this.addSpinnerStyle(spinner, this.questionData.getStyle());

        this.widgetValue.accept(new BaseValueVisitor<Void>() {
            @Override
            public Void visit(IntValue  node) {
                SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (int)SPINNER_MAX_VALUE, node.getIntValue());
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


        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Value updatedValue = this.widgetValue.add(this.incrementStep);
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), updatedValue);
            } catch (EvaluationException e) {
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), new UndefinedValue());

            }
        });

        return spinner;
    }

    private void addSpinnerStyle(Spinner spinner, Style style) {
        if (style == null)
            return;

        if (style.getWidth() != null) {
            spinner.setMinWidth(style.getWidth());
        } else {
            spinner.setMinWidth(Widget.TEXT_WIDTH); //default style
        }
    }
}
