package nl.khonraad.ql.gui.visuals;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.ast.data.Questionnaire;

@SuppressWarnings("serial")
public class ComputedValue extends JLabel {

    public ComputedValue(JPanel mainPanel, Question question, Questionnaire questionnaire) {

        super( question.string() );

        setFont( new Font( "Courier", Font.BOLD, 16 ) );
    }
}
