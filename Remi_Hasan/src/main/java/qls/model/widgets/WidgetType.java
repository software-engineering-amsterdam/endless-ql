package qls.model.widgets;

import ql.model.expression.ReturnType;

public enum WidgetType {
    // TODO: implement enum for type checking
    SLIDER, SPINBOX, TEXT, RADIO, CHECKBOX, DROPDOWN;

    public boolean isCompatible(ReturnType returnType) {
        switch (returnType) {
            case BOOLEAN:
                return this == CHECKBOX || this == DROPDOWN || this == RADIO;
            case STRING:
                return this == TEXT;
            case INTEGER:
            case NUMBER:
            case DECIMAL:
            case MONEY:
                return this == SLIDER || this == SPINBOX || this == TEXT;
            case DATE:
                // Date widget cannot be set, should always be date picker
                return false;
        }

        return false;
    }
}
