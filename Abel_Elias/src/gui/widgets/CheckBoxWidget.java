package gui.widgets;

import QL.classes.values.BooleanValue;
import javax.swing.*;

public class CheckBoxWidget implements Widget {
    private JCheckBox checkBox;
    private BooleanValue value;

    CheckBoxWidget(BooleanValue value){
        this.value = value;
        checkBox = new JCheckBox();
        refresh();
    }

    @Override
    public JCheckBox getJComponent() {
        return checkBox;
    }

    @Override
    public void refresh() {
        checkBox.setSelected(value.getValue());
    }
}
