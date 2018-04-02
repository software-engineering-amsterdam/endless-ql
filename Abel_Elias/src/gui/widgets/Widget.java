package gui.widgets;

import javax.swing.*;

public interface Widget<T extends JComponent> {
    //TODO: Add question listeners

    public T getJComponent();

    public void refresh();
}
