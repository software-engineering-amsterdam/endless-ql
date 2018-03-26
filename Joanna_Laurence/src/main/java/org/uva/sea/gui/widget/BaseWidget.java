package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.controller.IQuestionValueUpdatedListener;
import org.uva.sea.gui.model.factory.DefaultValueFactory;
import org.uva.sea.gui.model.factory.WidgetValueAssigner;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class BaseWidget extends BaseRenderable {

    protected final QuestionData questionData;

    private final Collection<IQuestionValueUpdatedListener> listeners = new ArrayList<>();

    private final DefaultValueFactory defaultValueFactory = new DefaultValueFactory();

    protected BaseWidget(final QuestionData questionData) {
        this.questionData = questionData;
    }

    public final void addListener(final IQuestionValueUpdatedListener listener) {
        this.listeners.add(listener);
    }

    protected final void sendUpdateValueEvent(final String identifier, final Value newValue) {
        for (final IQuestionValueUpdatedListener listener : this.listeners)
            listener.updateGuiVariable(identifier, newValue);
    }

    @Override
    public final Node render(final Map<String, VBox> containers) {
        final VBox container = containers.get(this.getContainerName());
        if (container != null) {
            final Node guiNode = this.convertToGuiNode();
            final Node widgetRow = this.drawComponent(this.questionData.getLabel(), guiNode);
            container.getChildren().add(widgetRow);
            return guiNode;
        }
        return null;
    }

    protected final BaseWidget linkToOtherWidget(final BaseWidget widget, final QuestionData questionData) {
        widget.addListener(this::sendUpdateValueEvent);
        final WidgetValueAssigner updater = new WidgetValueAssigner(widget);
        Value value = questionData.getValue();
        if (value == null)
            value = this.defaultValueFactory.getDefaultValue(questionData.getNodeType());
        updater.updateWidget(value);
        return widget;
    }

    public final String getIdentifier() {
        return this.questionData.getQuestionName();
    }

    public abstract Node convertToGuiNode();

    protected abstract String getContainerName();
}
