package org.uva.sea.gui.newImpl.model.factory;

import org.uva.sea.gui.newImpl.IGuiElementUpdateListener;
import org.uva.sea.gui.newImpl.widget.Widget;
import org.uva.sea.gui.newImpl.widget.qls.ChoiceBoxWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class QLSWidgetFactory {

    private final Map<WidgetType, Class<? extends Widget>> widgetTypeClassEnumMap = new HashMap<>();

    public QLSWidgetFactory() {
        this.widgetTypeClassEnumMap.put(WidgetType.CHECKBOX, ChoiceBoxWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.SLIDER, SliderWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.CHOICEBOX, ChoiceBoxWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.RADIO, RadioWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.SPINBOX, SpinBoxWidget.class);
        this.widgetTypeClassEnumMap.put(WidgetType.TEXTFIELD, TextFieldWidget.class);
    }

    public Widget createWidget(WidgetType widgetType, QuestionData questionData, IGuiElementUpdateListener listener) {

        Class<? extends Widget> widget = this.widgetTypeClassEnumMap.get(widgetType);
        if(widget == null) {
            //TODO: Other exception
            throw new NotImplementedException();
        }

        try {
            Widget newWidget = widget.getDeclaredConstructor(String.class, String.class, Style.class).newInstance(questionData.getLabel(), questionData.getQuestionName(), questionData.getStyle());
            newWidget.addListener(listener);
            WidgetValueUpdate widgetValueUpdate = new WidgetValueUpdate(newWidget);
            return widgetValueUpdate.updateWidget(questionData.getValue());
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            return null;
        }
    }
}
