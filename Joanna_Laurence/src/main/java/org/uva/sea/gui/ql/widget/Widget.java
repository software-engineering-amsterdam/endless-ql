package org.uva.sea.gui.ql.widget;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.model.factory.DefaultValueFactory;
import org.uva.sea.gui.ql.model.factory.WidgetValueUpdate;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class Widget extends Renderable {

    protected final QuestionData questionData;

    private final Collection<IGuiElementUpdateListener> listeners = new ArrayList<>();

    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    private Node guiElement;

    public Widget(QuestionData questionData) {
        this.questionData = questionData;
    }

    public void addListener(IGuiElementUpdateListener listener) {
        this.listeners.add(listener);
    }

    protected void sendUpdateValueEvent(String identifier, Value newValue) {
        for(IGuiElementUpdateListener listener : this.listeners )
            listener.updateGuiVariable(identifier, newValue);
    }

    @Override
    public Node render(Map<String, VBox> containers) {
        VBox container = containers.get(this.drawInContainer());
        if(container != null) {
            Node guiNode = this.convertToGuiNode();
            Node widgetRow = this.drawComponent(this.questionData.getLabel(), guiNode);
            container.getChildren().add(widgetRow);
            return guiNode;
        }
        return null;
    }

    public Widget linkToOtherWidget(Widget widget, QuestionData questionData) {
        DefaultValueFactory defaultValueFactory = new DefaultValueFactory();
        widget.addListener(this::sendUpdateValueEvent);
        WidgetValueUpdate updater = new WidgetValueUpdate(widget);
        Value value = questionData.getValue();
        if(value == null)
            value = defaultValueFactory.getDefaultValue(questionData.getNodeType());
        updater.updateWidget(value);
        return widget;
    }

    public String getIdentifier() {
        return this.questionData.getQuestionName();
    }

    public abstract Node convertToGuiNode();

    public abstract String drawInContainer();
}
