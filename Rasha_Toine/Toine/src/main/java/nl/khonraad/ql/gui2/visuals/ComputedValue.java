package nl.khonraad.ql.gui2.visuals;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.khonraad.ql.dynamics.Question;
import nl.khonraad.ql.dynamics.Questionnaire;

@SuppressWarnings("serial")
public class ComputedValue extends JLabel {

    public ComputedValue(JPanel mainPanel, Question question, Questionnaire questionnaire) {

        super( question.getValue().getText() );
        
        setFont( new Font( "Courier", Font.BOLD, 16 ) );
    }
}
