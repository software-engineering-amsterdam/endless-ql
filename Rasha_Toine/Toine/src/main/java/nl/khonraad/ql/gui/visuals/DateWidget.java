package nl.khonraad.ql.gui.visuals;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import nl.khonraad.ql.cdi.QuestionnaireAccessor;
import nl.khonraad.ql.domain.Question;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class DateWidget implements QuestionnaireAccessor {

    JTextField jTextField;
    
    public DateWidget(Question question) {
        
        jTextField = new JTextField( question.string(), 10 );

        jTextField.addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire().storeAnswer( question, Value.typed( Type.Date, current ) );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // Do nothing
            }
        } );
    }
}
