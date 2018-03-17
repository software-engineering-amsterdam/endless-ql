package qls.model.widget;

import ql.model.expression.ReturnType;

public enum WidgetType {
    // TODO: implement text, dropdown and slider
    SLIDER, SPINBOX, TEXTBOX, RADIO, CHECKBOX, DROPDOWN, DEFAULT;

    public boolean isCompatible(ReturnType returnType) {
        switch (returnType) {
            case BOOLEAN:
                return this == CHECKBOX || this == DROPDOWN || this == RADIO;
            case STRING:
                return this == TEXTBOX;
            case INTEGER:
            case NUMBER:
            case DECIMAL:
            case MONEY:
                return this == SLIDER || this == SPINBOX || this == TEXTBOX;
            case DATE:
                // Date widget cannot be set, should always be date picker
                return false;
        }

        return false;
    }
}
