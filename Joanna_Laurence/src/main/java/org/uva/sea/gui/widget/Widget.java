package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.IGuiElementUpdateListener;
import org.uva.sea.gui.components.Renderable;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Widget extends Renderable {

    protected QuestionData questionData;

    private List<IGuiElementUpdateListener> listeners = new ArrayList<>();

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
    public void render(VBox container, TabPane tabPane, VBox messages) {
        Node node = this.convertToGuiNode();
        if(node != null)
            container.getChildren().add(node);
    }

    public abstract Node convertToGuiNode();
}
