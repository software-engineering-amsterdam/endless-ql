package GUI.View;

import Nodes.Type;

import javax.swing.*;
import java.awt.*;

public class Widget {
    private final Type type;
    private final Component component;

    Widget(Type type, Component component) {
        this.type = type;
        this.component = component;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        try {
            switch(this.type) {
                case BOOL: return (T) (Boolean) (((JCheckBox) this.component).isSelected());
                case STRING: return (T) (((JTextField) this.component).getText());
                case MONEY: return (T) (Float) Float.parseFloat(((JTextField) this.component).getText());
                case INT: return (T) (Float) Float.parseFloat(((JTextField) this.component).getText());
                case DECIMAL: return (T) (Float) Float.parseFloat(((JTextField) this.component).getText());
                case DATE: return (T) (((JTextField) this.component).getText());
                default: return null;
            }
        } catch(Exception e) {
            return null;
        }
    }

    public Component getComponent() {
        return component;
    }

    public Type getType() {
        return type;
    }
}
