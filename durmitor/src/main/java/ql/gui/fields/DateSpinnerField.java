package ql.gui.fields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ql.ast.expression.Identifier;
import ql.ast.expression.literal.DateLiteral;

public class DateSpinnerField extends JSpinner {

    private static final long serialVersionUID = 7952307978295766801L;
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.date");
    
    public DateSpinnerField(final Identifier id) {
        super(new SpinnerDateModel());
        
        final SimpleDateFormat dateFormat = ((JSpinner.DateEditor) this.getEditor()).getFormat();
        dateFormat.setTimeZone(TimeZone.getDefault());
        dateFormat.applyPattern(translations.getString("date.pattern"));
        
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this, translations.getString("date.pattern"));
        this.setEditor(dateEditor);
//        this.setValue(new Date(-1));
        this.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Date value = (Date) ((JSpinner) e.getSource()).getValue();
                id.setValue(new DateLiteral(value));
            }
        });
    }
}
