
package nl.khonraad.ql.gui.visuals;

import java.awt.Font;

import javax.swing.JLabel;

import nl.khonraad.ql.language.Question;

public class ComputedQuestionWidget {

    JLabel jLabel;

    public ComputedQuestionWidget( Question question ) {

        jLabel = new JLabel( question.string() );

        jLabel.setFont( new Font( "Courier", Font.BOLD, 16 ) );
    }
}
