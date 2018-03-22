package org.uva.sea.gui.ql.model.factory;

import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.gui.ql.widget.qls.*;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.parser.NodeType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class QLSWidgetFactory extends QLWidgetFactory {

    private final Map<WidgetType, Class<? extends Widget>> widgetTypeClasses = new HashMap<>();
    private final Map<NodeType, Class<? extends Widget>> nodeTypeClasses = new HashMap<>();

    public QLSWidgetFactory() {
        this.widgetTypeClasses.put(WidgetType.CHECKBOX, CheckBoxWidgetQLS.class);
        this.widgetTypeClasses.put(WidgetType.SLIDER, SliderWidget.class);
        this.widgetTypeClasses.put(WidgetType.CHOICEBOX, ChoiceBoxWidget.class);
        this.widgetTypeClasses.put(WidgetType.RADIO, RadioButtonWidget.class);
        this.widgetTypeClasses.put(WidgetType.SPINBOX, SpinnerWidget.class);
        this.widgetTypeClasses.put(WidgetType.TEXTFIELD, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.BOOLEAN, CheckBoxWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.DECIMAL, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.INTEGER, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.MONEY_EURO, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.MONEY_DOLLAR, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.DATE, TextFieldWidgetQLS.class);
        this.nodeTypeClasses.put(NodeType.STRING, TextFieldWidgetQLS.class);
    }

    @Override
    public Widget createWidget(QuestionData questionData, IGuiElementUpdateListener listener) {
        WidgetType widgetType = questionData.getWidgetType();
        if(widgetType == WidgetType.DEFAULT)
            return this.getNodeTypedWidget(questionData.getNodeType(), questionData, listener);

        return this.getWidgetTypedWidget(widgetType, questionData, listener);
    }

    private Widget getWidgetTypedWidget(WidgetType widgetType, QuestionData questionData, IGuiElementUpdateListener listener) {
        Class<? extends Widget> widget = this.widgetTypeClasses.get(widgetType);
        if (widget == null) {
            return this.createWidget(questionData, listener);
        }

        return this.getWidget(questionData, listener, widget);
    }

    private Widget getNodeTypedWidget(NodeType nodeType, QuestionData questionData, IGuiElementUpdateListener listener) {
        Class<? extends Widget> widget = this.nodeTypeClasses.get(nodeType);
        if (widget == null) {
            throw new NotImplementedException();
        }

        return this.getWidget(questionData, listener, widget);
    }
}
