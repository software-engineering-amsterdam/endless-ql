package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class SliderWidgetQLS extends WidgetQLS {

    private static final double DECIMAL_STEP_SIZE = 0.1;

    private static final int INTEGER_STEP_SIZE = 1;

    private Value widgetValue = new DecimalValue(0);

    private Value incrementStep = new DecimalValue(SliderWidgetQLS.DECIMAL_STEP_SIZE);

    public SliderWidgetQLS(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DecimalValue decimalValue) {
        this.widgetValue = decimalValue;
        this.incrementStep = new DecimalValue(SliderWidgetQLS.DECIMAL_STEP_SIZE);
        return true;
    }

    @Override
    public boolean updateValue(IntValue intValue) {
        this.widgetValue = intValue;
        this.incrementStep = new IntValue(SliderWidgetQLS.INTEGER_STEP_SIZE);
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setValueChanging(true);
        slider.setMinorTickCount(1);
        slider.setMax(100);

        if (this.questionData.getValue() != null) {
            this.widgetValue.accept(new BaseValueVisitor<Void>() {
                @Override
                public Void visit(DecimalValue value) {
                    slider.valueProperty().setValue(value.getDecimalValue());
                    return null;
                }

                @Override
                public Void visit(IntValue value) {
                    slider.valueProperty().setValue(value.getIntValue());
                    return null;
                }
            });

        }

        slider.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Value newWidgetValue;
                try {
                    newWidgetValue = this.widgetValue.add(this.incrementStep);
                } catch (EvaluationException ignored) {
                    newWidgetValue = this.widgetValue;
                }
                this.sendUpdateValueEvent(this.questionData.getQuestionName(), newWidgetValue);
            }
        });

        this.setStyle(slider);

        return slider;
    }
}


