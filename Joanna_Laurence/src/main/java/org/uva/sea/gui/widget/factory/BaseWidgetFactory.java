package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

public abstract class BaseWidgetFactory {

    public static BaseWidgetFactory getWidgetFactory(WidgetType widgetType, FormController controller) {
        BaseWidgetFactory factory = null;
        switch (widgetType) {
            case SLIDER:
                factory = new SliderWidgetFactory(controller);
                break;
            case CHECKBOX:
                factory = new CheckBoxWidgetFactory(controller);
                break;
            case CHOICEBOX:
                factory = new ChoiceBoxWidgetFactory(controller);
                break;
            case RADIO:
                factory = new RadioButtonWidgetFactory(controller);
                break;
            case SPINBOX:
                factory = new SpinnerWidgetWidgetFactory(controller);
                break;
            case TEXTFIELD:
                factory = new TextFieldWidgetFactory(controller);
                break;
            default:
            case DEFAULT:
            case UNKNOWN:
                factory = new DefaultWidgetFactory(controller);
                break;
        }
        return factory;
    }

    public abstract Control createWidget(BaseQuestionModel questionModel);
}
