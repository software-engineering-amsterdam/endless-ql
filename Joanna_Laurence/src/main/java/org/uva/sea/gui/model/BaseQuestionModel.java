package org.uva.sea.gui.model;

import javafx.scene.control.Control;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.parser.NodeType;

public abstract class BaseQuestionModel extends Control implements BaseQuestionGUI {

    private String label;
    private NodeType type;
    private Value value;
    private String variableName;
    private boolean isComputed;
    private WidgetType widgetType;

    public BaseQuestionModel(QuestionData data) {
        this.label = data.getLabel();
        this.type = data.getNodeType();
        this.value = data.getValue();
        this.isComputed = data.isComputed();
        this.variableName = data.getQuestionName();
        this.widgetType = data.getWidgetType();
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getLabel() {
        return label;
    }

    public NodeType getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public boolean isComputed() {
        return isComputed;
    }

}
