package nl.khonraad.ql.gui.visuals;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.dynamics.Question;
import nl.khonraad.ql.dynamics.Questionnaire;
import nl.khonraad.ql.gui.QLInterpretor;

@SuppressWarnings("serial")
public class DateWidget extends JTextField {

    public DateWidget(JPanel mainPanel, Question question, Questionnaire questionnaire) {
        super( question.getValue().getText(), 10);

        addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire.storeAnswer( question.getIdentifier(), new Value( Type.Date, current ) );
                QLInterpretor.visualizeQuestionnaire( questionnaire, mainPanel );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // YAGNI
            }
        } );    }

}
