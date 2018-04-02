package nl.khonraad.ql.gui.visuals;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdi.QuestionnaireAccessor;

public class DateWidget implements QuestionnaireAccessor {

    JTextField jTextField;
    
    public DateWidget(Question question) {
        
        jTextField = new JTextField( question.string(), 10 );

        jTextField.addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire().storeAnswer( question.identifier(), new Value( Type.Date, current ) );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // Do nothing
            }
        } );
    }
}
