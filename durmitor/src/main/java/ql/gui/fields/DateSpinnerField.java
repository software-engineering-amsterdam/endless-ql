package ql.gui.fields;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ql.ast.type.Date;

public class DateSpinnerField extends JSpinner {

    private static final long serialVersionUID = 7952307978295766801L;
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.date");
    
    public DateSpinnerField(final Date date) {
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
                LocalDate localDate = (LocalDate) ((JSpinner) e.getSource()).getValue();
                date.setDate(localDate);
            }
        });
    }
}
