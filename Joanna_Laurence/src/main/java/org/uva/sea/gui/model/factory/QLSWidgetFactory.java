package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.IGuiElementUpdateListener;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.gui.widget.ql.TextFieldWidget;
import org.uva.sea.gui.widget.qls.ChoiceBoxWidget;
import org.uva.sea.gui.widget.qls.RadioButtonWidget;
import org.uva.sea.gui.widget.qls.SliderWidget;
import org.uva.sea.gui.widget.qls.SpinnerWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class QLSWidgetFactory extends QLWidgetFactory {

    private final Map<WidgetType, Class<? extends Widget>> widgetTypeClassEnumMap = new HashMap<>();

    public QLSWidgetFactory() {
        this.widgetTypeClassEnumMap.put(WidgetType.CHECKBOX, ChoiceBoxWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.SLIDER, SliderWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.CHOICEBOX, ChoiceBoxWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.RADIO, RadioButtonWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.SPINBOX, SpinnerWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.TEXTFIELD, TextFieldWidget.class);
    }

    public Widget createWidget(WidgetType widgetType, QuestionData questionData, IGuiElementUpdateListener listener) {

        Class<? extends Widget> widget = this.widgetTypeClassEnumMap.get(widgetType);
        if (widget == null) {
            return this.createWidget(questionData.getNodeType(), questionData, listener);
        }

        return this.getWidget(questionData, listener, widget);
    }
}
