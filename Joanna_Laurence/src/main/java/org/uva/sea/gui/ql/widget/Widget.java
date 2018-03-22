package org.uva.sea.gui.ql.widget;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
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

    public Widget(QuestionData questionData) {
        this.questionData = questionData;
    }

    public void addListener(IGuiElementUpdateListener listener) {
        this.listeners.add(listener);
    }

    protected void sendUpdateValueEvent(Control control, String identifier, Value newValue) {
        for(IGuiElementUpdateListener listener : this.listeners )
            listener.updateGuiVariable(control, identifier, newValue);
    }


    @Override
    public void render(Map<String, VBox> containers) {
        Pane container = containers.get(this.drawInContainer());
        if(container != null) {
            container.getChildren().add(this.convertToGuiNode());
        }
    }

    public Widget linkToOtherWidget(Widget checkBoxWidget, QuestionData questionData) {
        DefaultValueFactory defaultValueFactory = new DefaultValueFactory();
        checkBoxWidget.addListener(this::sendUpdateValueEvent);
        WidgetValueUpdate updater = new WidgetValueUpdate(checkBoxWidget);
        Value value = questionData.getValue();
        if(value == null)
            value = defaultValueFactory.getDefaultValue(questionData.getNodeType());
        updater.updateWidget(value);
        return checkBoxWidget;
    }

    public abstract Node convertToGuiNode();

    public abstract String drawInContainer();
}
