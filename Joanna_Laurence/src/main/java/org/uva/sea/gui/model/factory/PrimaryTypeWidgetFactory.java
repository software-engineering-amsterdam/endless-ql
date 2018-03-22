package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.controller.IGuiElementUpdateListener;
import org.uva.sea.gui.widget.*;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PrimaryTypeWidgetFactory implements IWidgetFactory {
    private final Map<NodeType, Class<? extends BaseWidget>> widgetTypeClassEnumMap = new HashMap<>();

    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    public PrimaryTypeWidgetFactory() {
        this.widgetTypeClassEnumMap.put(NodeType.BOOLEAN, CheckBoxWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.DECIMAL, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.INTEGER, TextFieldWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.MONEY_EURO, MoneyWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.MONEY_DOLLAR, MoneyWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.DATE, DateWidget.class);
        this.widgetTypeClassEnumMap.put(NodeType.STRING, TextFieldWidget.class);
    }

    @Override
    public BaseWidget createWidget(QuestionData questionData, IGuiElementUpdateListener listener) {
        Class<? extends BaseWidget> widget = this.widgetTypeClassEnumMap.get(questionData.getNodeType());
        if (widget == null) {
            //TODO: Other exception
            throw new NotImplementedException();
        }

        return this.getWidget(questionData, listener, widget);
    }

    protected BaseWidget getWidget(QuestionData questionData, IGuiElementUpdateListener listener, Class<? extends BaseWidget> widget) {
        try {
            BaseWidget newWidget = widget.getDeclaredConstructor(QuestionData.class).newInstance(questionData);
            newWidget.addListener(listener);
            WidgetValueUpdater widgetValueUpdate = new WidgetValueUpdater(newWidget);
            Value questionValue = questionData.getValue();
            if (questionValue == null)
                questionValue = this.defaultValueFactory.getDefaultValue(questionData.getNodeType());

            widgetValueUpdate.updateWidget(questionValue);
            return newWidget;
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
