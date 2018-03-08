package ql.gui.fields.actionlisteners;

import java.awt.Component;
import java.awt.Container;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

public abstract class AbstractActionListener extends AbstractAction {

    private static final long serialVersionUID = 2395752283005424718L;
    
    public static void enablePanel(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for(Component component : components) {
            component.setEnabled(enable);
            if(component instanceof Container) {
                enablePanel((Container) component, enable);
            }
        }
    }
    
    public static void setVisibility(Container container, boolean visible) {
        container.setVisible(visible);
    }
    
    public void resetPanel(Container container) {
        Component[] components = container.getComponents();
        for(Component component : components) {
            if(component instanceof Container) {
                resetPanel((Container) component);
            }
            
            // CheckBox
            try {
                JCheckBox checkBox = (JCheckBox) component;
                checkBox.setSelected(false);
            } catch (ClassCastException e) {}
        }
    }
 }
