package org.uva.sea.gui.widget;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.IGuiElementUpdateListener;
import org.uva.sea.gui.model.factory.DefaultValueFactory;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

import java.util.ArrayList;
import java.util.Collection;

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
    public Node render(Group container, TabPane tabPane, VBox messages) {
        return this.convertToGuiNode();
    }

    public abstract Node convertToGuiNode();
}
