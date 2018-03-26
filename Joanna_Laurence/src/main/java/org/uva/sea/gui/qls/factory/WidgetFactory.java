package org.uva.sea.gui.qls.factory;

import org.uva.sea.gui.controller.IQuestionValueUpdatedListener;
import org.uva.sea.gui.model.factory.PrimaryTypeWidgetFactory;
import org.uva.sea.gui.model.factory.WidgetNotFoundException;
import org.uva.sea.gui.qls.widget.*;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.parser.NodeType;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory extends PrimaryTypeWidgetFactory {

    private final Map<WidgetType, Class<? extends BaseWidget>> widgetTypeClasses = new HashMap<>();
    private final Map<NodeType, Class<? extends BaseWidget>> nodeTypeClasses = new HashMap<>();

    public WidgetFactory() {
        this.widgetTypeClasses.put(WidgetType.CHECKBOX, CheckBoxWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.SLIDER, SliderWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.CHOICEBOX, ChoiceBoxWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.RADIO, RadioButtonWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.SPINBOX, SpinnerWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.TEXTFIELD, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.BOOLEAN, CheckBoxWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.DECIMAL, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.INTEGER, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.MONEY_EURO, MoneyWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.MONEY_DOLLAR, MoneyWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.DATE, DateWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.STRING, TextFieldWidgetQLS.class);
    }

    @Override
    public final BaseWidget createWidget(final QuestionData questionData, final IQuestionValueUpdatedListener listener) throws WidgetNotFoundException {
        final WidgetType widgetType = questionData.getWidgetType();
        if (widgetType == WidgetType.DEFAULT)
            return this.getNodeTypedWidget(questionData.getNodeType(), questionData, listener);
        return this.getWidgetTypedWidget(widgetType, questionData, listener);
    }

    private BaseWidget getWidgetTypedWidget(final WidgetType widgetType, final QuestionData questionData, final IQuestionValueUpdatedListener listener) throws WidgetNotFoundException {
        final Class<? extends BaseWidget> widget = this.widgetTypeClasses.get(widgetType);
        if (widget == null) {
            throw new WidgetNotFoundException("Widget for widget type " + widgetType + " not found");
        }

        return this.getWidget(widget, questionData, listener);
    }

    private BaseWidget getNodeTypedWidget(final NodeType nodeType, final QuestionData questionData, final IQuestionValueUpdatedListener listener) throws WidgetNotFoundException {
        final Class<? extends BaseWidget> widget = this.nodeTypeClasses.get(nodeType);
        if (widget == null) {
            throw new WidgetNotFoundException("Widget for node type " + nodeType + " not found");
        }

        return this.getWidget(widget, questionData, listener);
    }
}
