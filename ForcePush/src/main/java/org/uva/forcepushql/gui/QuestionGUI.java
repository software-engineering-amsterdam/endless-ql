package org.uva.forcepushql.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface QuestionGUI {

    public JLabel getLabel();

    public String getVariable();

    public void addActionListener(ActionListener actionListener);

    public void attachObserver(Observer observer);

    public void notifyAllObservers();
}
