package com.chariotit.uva.sc.qdsl.formbuilder;

import javax.swing.*;

public class FormQuestion {

    private JLabel label;
    private JComponent component;
    private Boolean visible;

    public FormQuestion(JLabel label, JComponent component) {
        this.label = label;
        this.component = component;
        this.setVisible(true);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
        this.component.setVisible(visible);
        this.label.setVisible(visible);
    }
}
