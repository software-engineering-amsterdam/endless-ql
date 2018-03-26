package nl.khonraad.ql.gui.visuals;

import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.dynamics.Question;
import nl.khonraad.ql.dynamics.Questionnaire;
import nl.khonraad.ql.gui.QLInterpretor;

@SuppressWarnings("serial")
public class MoneyWidget extends JSpinner {

    public MoneyWidget(JPanel mainPanel, Question question, Questionnaire questionnaire) {

        super( new SpinnerNumberModel( new Double( question.getValue().getText() ), null, null, 0.01 ) );

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) getEditor();
        
        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 2 );
        format.setMinimumFractionDigits( 2 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        Dimension d = getPreferredSize();
        d.width = 185;
        setPreferredSize( d );
        
        addChangeListener( e -> {

            JSpinner s = (JSpinner) e.getSource();

            String c = s.getModel().getValue().toString();

            questionnaire.storeAnswer( question.getIdentifier(), new Value( Type.Money, c ) );
            
            QLInterpretor.visualizeQuestionnaire( questionnaire, mainPanel );
        } );
    }

}
