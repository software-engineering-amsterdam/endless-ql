package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.factory.visitor.QlsWidgetFromValueVisitor;
import org.uva.sea.gui.widget.*;

public class QlsWidgetFactory extends DefaultWidgetFactory {

    public QlsWidgetFactory(FormController controller) {
        super(controller);
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        Widget widget;
        switch (questionModel.getWidgetType()) {
            case SPINBOX:
                widget = new SpinnerWidget(questionModel, this. controller);
                break;
            case RADIO:
                widget = new RadioButtonWidget(questionModel, this. controller);
                break;
            case CHOICEBOX:
                widget = new ChoiceBoxWidget(questionModel, this. controller);
                break;
            case CHECKBOX:
                widget = new CheckBoxWidget(questionModel, controller);
                break;
            case SLIDER:
                widget = new SliderWidget(questionModel, this. controller);
                break;
            case DEFAULT:
            case UNKNOWN:
            case TEXTFIELD:
            default:
                widget = new TextFieldWidget(questionModel, this. controller);
                break;
        }
        return questionModel.accept(new QlsWidgetFromValueVisitor(controller, widget));
    }
}
