package ql.gui.fields;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.PlainDocument;

import ql.ast.expression.Identifier;
import ql.gui.fields.document.filters.IntFilter;

public class IntTextField extends JTextField {

    private static final long serialVersionUID = 3967414248224464413L;

    public IntTextField(final Identifier identifier) {
        super();
        this.setName(identifier.getName());
        this.setText(identifier.getValue().getValue().toString());
        PlainDocument document = (PlainDocument) this.getDocument();
        document.setDocumentFilter(new IntFilter(identifier.getType(), "^[0-9]+$"));
        
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
