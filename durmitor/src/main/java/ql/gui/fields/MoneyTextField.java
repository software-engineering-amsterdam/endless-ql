package ql.gui.fields;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.PlainDocument;

import ql.ast.type.Money;
import ql.gui.fields.document.filters.MoneyFilter;

public class MoneyTextField extends JTextField {

    private static final long serialVersionUID = -3141780028784984723L;

    public MoneyTextField(final Money money) {
        super();
        this.setName(money.getName());
        this.setText("0.00");
        PlainDocument doc = (PlainDocument) this.getDocument();
        doc.setDocumentFilter(new MoneyFilter(money, "^[0-9]+[.]?[0-9]{0,2}$"));
        
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
            
            @Override
            public void focusLost(FocusEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String value = String.format("%.2f", Double.parseDouble(getText()));
                        value = value.replace(",", ".");
                        setText(value);
                    }
                });
            }
        });
    }
}
