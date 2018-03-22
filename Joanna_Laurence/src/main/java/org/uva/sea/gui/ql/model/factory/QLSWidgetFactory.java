package org.uva.sea.gui.ql.model.factory;

import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.gui.ql.widget.ql.TextFieldWidget;
import org.uva.sea.gui.ql.widget.qls.ChoiceBoxWidget;
import org.uva.sea.gui.ql.widget.qls.RadioButtonWidget;
import org.uva.sea.gui.ql.widget.qls.SliderWidget;
import org.uva.sea.gui.ql.widget.qls.SpinnerWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

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
