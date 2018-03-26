package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.controller.IQuestionValueUpdatedListener;
import org.uva.sea.gui.widget.*;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PrimaryTypeWidgetFactory implements IWidgetFactory {
    private final Map<NodeType, Class<? extends BaseWidget>> nodeTypeToWidgetMap = new HashMap<>();

    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    public PrimaryTypeWidgetFactory() {
        this.nodeTypeToWidgetMap.put(NodeType.BOOLEAN, CheckBoxWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.DECIMAL, TextFieldWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.INTEGER, TextFieldWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.MONEY_EURO, MoneyWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.MONEY_DOLLAR, MoneyWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.DATE, DateWidget.class);
        this.nodeTypeToWidgetMap.put(NodeType.STRING, TextFieldWidget.class);
    }

    @Override
    public BaseWidget createWidget(final QuestionData questionData, final IQuestionValueUpdatedListener listener) throws WidgetNotFoundException {
        final NodeType nodeType = questionData.getNodeType();
        final Class<? extends BaseWidget> widgetClass = this.nodeTypeToWidgetMap.get(nodeType);
        if (widgetClass == null) {
            throw new WidgetNotFoundException("Widget for node type " + nodeType + " not found");
        }

        return this.getWidget(widgetClass, questionData, listener);
    }

    protected final BaseWidget getWidget(final Class<? extends BaseWidget> widget, final QuestionData questionData, final IQuestionValueUpdatedListener listener) {
        try {
            final BaseWidget newWidget = widget.getDeclaredConstructor(QuestionData.class).newInstance(questionData);
            newWidget.addListener(listener);

            Value questionValue = questionData.getValue();
            if (questionValue == null) {
                questionValue = this.defaultValueFactory.getDefaultValue(questionData.getNodeType());
            }

            //Set supported Value to the widget
            final WidgetValueAssigner widgetValueUpdate = new WidgetValueAssigner(newWidget);
            widgetValueUpdate.updateWidget(questionValue);
            return newWidget;
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException ignored) {
            return null;
        }
    }
}
