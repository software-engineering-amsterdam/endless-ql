package org.uva.sea.gui.model;

import javafx.scene.control.Control;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;

public abstract class BaseQuestionModel extends Control implements BaseQuestionGUI {

    private final String label;
    private final NodeType type;
    private final Value value;
    private final String variableName;
    private final boolean isComputed;
    private final WidgetType widgetType;
    private final Style styleQLS;

    public BaseQuestionModel(final QuestionData data) {
        this.label = data.getLabel();
        this.type = data.getNodeType();
        this.value = data.getValue();
        this.isComputed = data.isComputed();
        this.variableName = data.getQuestionName();
        this.widgetType = data.getWidgetType();
        this.styleQLS = data.getStyle();
    }

    public Style getStyleQLS() {
        return this.styleQLS;
    }

    public WidgetType getWidgetType() {
        return this.widgetType;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public String getLabel() {
        return this.label;
    }

    public NodeType getType() {
        return this.type;
    }

    public Value getValue() {
        return this.value;
    }

    public boolean isComputed() {
        return this.isComputed;
    }

}
