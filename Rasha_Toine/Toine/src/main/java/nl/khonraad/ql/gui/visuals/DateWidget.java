package nl.khonraad.ql.gui.visuals;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import nl.khonraad.ql.cdi.InterpretorAccessor;
import nl.khonraad.ql.language.Question;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class DateWidget implements InterpretorAccessor {

    JTextField jTextField;
    
    public DateWidget(Question question) {
        
        jTextField = new JTextField( question.string(), 10 );

        jTextField.addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire().assigns( question, Value.typed( Type.Date, current ) );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // Do nothing
            }
        } );
    }
}
