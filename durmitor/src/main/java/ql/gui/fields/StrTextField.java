package ql.gui.fields;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ql.ast.type.Str;

public class StrTextField extends JTextField {

    private static final long serialVersionUID = -6847803454758129428L;
    
    public StrTextField(final Str string) {
        super();
        this.setName(string.getName());
        this.setText(string.getValue());
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        selectAll();
                    }
                });
            }
        });
    }
}
