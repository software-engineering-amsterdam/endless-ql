package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.IGuiElementUpdateListener;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.gui.widget.ql.CheckBoxWidget;
import org.uva.sea.gui.widget.ql.TextFieldWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class QLWidgetFactory {
    private final Map<NodeType, Class<? extends Widget>> widgetTypeClassEnumMap = new HashMap<>();

    public QLWidgetFactory() {
        this.widgetTypeClassEnumMap.put(NodeType.BOOLEAN, CheckBoxWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.DECIMAL, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.INTEGER, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.MONEY_EURO, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.MONEY_DOLLAR, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.DATE, TextFieldWidget.class);
    }

    public Widget createWidget(NodeType nodeType, QuestionData questionData, IGuiElementUpdateListener listener) {

        Class<? extends Widget> widget = this.widgetTypeClassEnumMap.get(nodeType);
        if(widget == null) {
            throw new NotImplementedException();
        }

        try {
            return widget.getDeclaredConstructor(String.class, String.class, Value.class).newInstance(questionData.getLabel(), questionData.getQuestionName(), questionData.getValue());
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            return null;
        }
    }
}
