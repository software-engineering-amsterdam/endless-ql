package GUI;

import Nodes.Type;

import javax.swing.*;
import java.awt.*;

public class Widget {
    private Type type;
    private Component component;

    public Widget(Type type, Component component){
        this.type = type;
        this.component = component;
    }

    public <T> T getValue(){
        switch (this.type){
            case BOOL: return (T) new Boolean(((JCheckBox)this.component).isSelected());
            case STRING: return (T) new String(((JTextField)this.component).getText());
            case MONEY: return (T) new Float(((JTextField)this.component).getText());
            case INT: return (T) new Float(((JTextField)this.component).getText());
            case DECIMAL: return (T) new Float(((JTextField)this.component).getText());
            case DATE: return (T) new Float(((JTextField)this.component).getText());
            default: return  null;
        }
    }

    public Component getComponent() {
        return component;
    }
}
