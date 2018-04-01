package gui.listeners;

import QL.classes.values.Value;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioActionListener implements ActionListener{
    Value value;

    public RadioActionListener(Value value){
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        value.setValueGeneric(input);
    }
}
