package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.parser.NodeType;

public enum WidgetType {
    UNKNOWN,

    DEFAULT,
    CHECKBOX,
    CHOICEBOX,
    RADIO,
    SLIDER,
    SPINBOX,
    TEXTFIELD;

    private boolean isBooleanCompatible(final NodeType nodeType) {
        return ((this == WidgetType.CHECKBOX) ||
                (this == WidgetType.CHOICEBOX) ||
                (this == WidgetType.RADIO)) &&
                (nodeType == NodeType.BOOLEAN);
    }

    private boolean isNumberCompatible(final NodeType nodeType) {
        return ((this == WidgetType.SLIDER) ||
                (this == WidgetType.SPINBOX)) && (
                (nodeType == NodeType.INTEGER) ||
                        (nodeType == NodeType.DECIMAL)
        );
    }

    private boolean isStringCompatible(final NodeType nodeType) {
        return (this == WidgetType.TEXTFIELD) && (
                (nodeType == NodeType.STRING) ||
                        (nodeType == NodeType.INTEGER) ||
                        (nodeType == NodeType.DECIMAL)
        );
    }

    private boolean isNotUnknown() {
        return this != WidgetType.UNKNOWN;
    }

    public boolean isCompatible(final NodeType nodeType) {
        return this.isNotUnknown() ||
                this.isBooleanCompatible(nodeType) ||
                this.isNumberCompatible(nodeType) ||
                this.isStringCompatible(nodeType);
    }
}
