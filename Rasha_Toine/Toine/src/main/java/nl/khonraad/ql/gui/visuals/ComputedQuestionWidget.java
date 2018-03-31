package nl.khonraad.ql.gui.visuals;

import java.awt.Font;

import javax.swing.JLabel;

import nl.khonraad.ql.ast.data.Question;

@SuppressWarnings( "serial" )

public class ComputedQuestionWidget extends JLabel {

    public ComputedQuestionWidget( Question question ) {

        super( question.string() );

        setFont( new Font( "Courier", Font.BOLD, 16 ) );
    }
}
