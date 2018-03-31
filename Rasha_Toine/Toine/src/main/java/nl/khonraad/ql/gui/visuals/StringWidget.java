package nl.khonraad.ql.gui.visuals;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdiSupport.QuestionnaireAccessor;

@SuppressWarnings("serial") 

public class StringWidget extends JTextField implements QuestionnaireAccessor {

    public StringWidget(Question question) {

        super( question.value().string(), 25 );

        addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire().storeAnswer( question.identifier(), new Value( Type.String, current ) );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // Do nothing
            }
        } );
    }
}
