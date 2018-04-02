package GUI.View;

import Nodes.Type;

import javax.swing.*;
import java.awt.*;

/**
 * The widget is the appropriate component for a QL Question (e.g. radio button for boolean, JTextfield for string, etc.)
 */

public class Widget {
    private final Type type;
    private final Component component;

    /**
     * Create an instance of a Widget with a set type and JFrame component
     * @param type
     * @param component
     */
    Widget(Type type, Component component) {
        this.type = type;
        this.component = component;
    }

    /**
     * Returns the value set in the JFrame component
     * @param <T>
     * @return Returns the value set or null if it isn't set yet
     */
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

    /**
     * Returns the JFrame component of the Widget
     * @return The JFrame component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Returns the type of the Widget
     * @return The type of the Widget (e.g. BOOL, INT, DECIMAL)
     */
    public Type getType() {
        return type;
    }
}
