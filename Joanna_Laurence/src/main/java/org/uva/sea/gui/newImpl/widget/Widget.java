package org.uva.sea.gui.newImpl.widget;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.newImpl.IGuiElementUpdateListener;
import org.uva.sea.gui.newImpl.components.Renderable;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
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
        container.getChildren().add(this.convertToGuiNode());
    }

    public abstract Node convertToGuiNode();
}
