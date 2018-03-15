package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.parser.NodeType;

public enum WidgetType {
    INVALID,
    UNKNOWN,

    DEFAULT,
    CHECKBOX,
    CHOICEBOX,
    RADIO,
    SLIDER,
    SPINBOX,
    TEXTFIELD;

    public boolean isBooleanCompatible(NodeType nodeType) {
        return (this == CHECKBOX ||
                this == CHOICEBOX ||
                this == RADIO) &&
                nodeType == NodeType.BOOLEAN;
    }

    public boolean isNumberCompatible(NodeType nodeType) {
        return  (this == SLIDER ||
                 this == SPINBOX ) && (
                     nodeType == NodeType.INTEGER ||
                    nodeType == NodeType.DECIMAL
                );
    }

    public boolean isStringCompatible(NodeType nodeType) {
        return this == TEXTFIELD && (
                    nodeType == NodeType.STRING ||
                    nodeType == NodeType.INTEGER ||
                    nodeType == NodeType.DECIMAL
                );
    }

    private boolean isNotUnknown() {
        return this != UNKNOWN;
    }

    public boolean isCompatible(NodeType nodeType) {
        return  isNotUnknown() ||
                isBooleanCompatible(nodeType) ||
                isNumberCompatible(nodeType) ||
                isStringCompatible(nodeType);
    }
}
