package ql.gui.fields;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.PlainDocument;

import ql.ast.type.Int;
import ql.gui.fields.document.filters.IntFilter;

public class IntTextField extends JTextField {

    private static final long serialVersionUID = 3967414248224464413L;

    public IntTextField(final Int variable) {
        super();
        this.setName(variable.getName());
        this.setText("0");
        PlainDocument document = (PlainDocument) this.getDocument();
        document.setDocumentFilter(new IntFilter(variable, "^[0-9]+$"));
        
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
