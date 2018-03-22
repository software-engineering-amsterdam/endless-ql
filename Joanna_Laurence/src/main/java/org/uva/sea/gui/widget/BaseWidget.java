package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.controller.IGuiElementUpdateListener;
import org.uva.sea.gui.model.factory.DefaultValueFactory;
import org.uva.sea.gui.model.factory.WidgetValueUpdater;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class BaseWidget extends BaseRenderable {

    protected final QuestionData questionData;

    private final Collection<IGuiElementUpdateListener> listeners = new ArrayList<>();

    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    public BaseWidget(QuestionData questionData) {
        this.questionData = questionData;
    }

    public void addListener(IGuiElementUpdateListener listener) {
        this.listeners.add(listener);
    }

    protected void sendUpdateValueEvent(String identifier, Value newValue) {
        for (IGuiElementUpdateListener listener : this.listeners)
            listener.updateGuiVariable(identifier, newValue);
    }

    @Override
    public Node render(Map<String, VBox> containers) {
        VBox container = containers.get(this.drawInContainer());
        if (container != null) {
            Node guiNode = this.convertToGuiNode();
            Node widgetRow = this.drawComponent(this.questionData.getLabel(), guiNode);
            container.getChildren().add(widgetRow);
            return guiNode;
        }
        return null;
    }

    public BaseWidget linkToOtherWidget(BaseWidget widget, QuestionData questionData) {
        DefaultValueFactory defaultValueFactory = new DefaultValueFactory();
        widget.addListener(this::sendUpdateValueEvent);
        WidgetValueUpdater updater = new WidgetValueUpdater(widget);
        Value value = questionData.getValue();
        if (value == null)
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
