package nl.khonraad.gui.interactions;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import nl.khonraad.ql.language.Question;
import nl.khonraad.cdi.accessors.QuestionsInterpretorAccessor;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class StringWidget implements QuestionsInterpretorAccessor {

    JTextField jTextField;

    public StringWidget( Question question ) {

        jTextField = new JTextField( question.value().string(), 25 );

        jTextField.addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionsInterpretor().assign( question, Value.typed( Type.String, current ) );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // Do nothing
            }
        } );
    }
}
