package nl.khonraad.ql.gui.visuals;

import java.awt.Font;

import javax.swing.JLabel;

import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.ast.data.Questionnaire;

@SuppressWarnings( "serial" )

public class ComputedQuestionWidget extends JLabel {

    public ComputedQuestionWidget( Question question, Questionnaire questionnaire ) {

        super( question.string() );

        setFont( new Font( "Courier", Font.BOLD, 16 ) );
    }
}
