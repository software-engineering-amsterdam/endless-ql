package gui.widgets;

import QL.classes.values.Value;
import gui.listeners.DropDownListener;
import gui.listeners.RadioActionListener;

import javax.swing.*;

public class RadioWidget implements Widget {
    private JPanel radioBox;
    private Value value;
    private ButtonGroup buttonGroup;

    public RadioWidget(Value value, Object[] options){
        this.value = value;
        radioBox = new JPanel();

        buttonGroup = new ButtonGroup();
        JRadioButton current;
        RadioActionListener listener = new RadioActionListener(value);

        for(Object option : options){
            current = new JRadioButton(option.toString());
            current.setActionCommand(option.toString());
            current.addActionListener(listener);

            radioBox.add(current);
            buttonGroup.add(current);
        }

        refresh();
    }

    @Override
    public JComponent getJComponent() {
        return radioBox;
    }

    @Override
    public void refresh() {
       //
    }
}
