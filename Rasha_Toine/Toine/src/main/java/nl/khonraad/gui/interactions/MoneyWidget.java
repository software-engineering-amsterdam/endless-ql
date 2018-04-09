package nl.khonraad.gui.interactions;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import nl.khonraad.cdi.accessors.QuestionsInterpretorAccessor;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.Question;

public class MoneyWidget implements QuestionsInterpretorAccessor {

    JSpinner jSpinner;

    public MoneyWidget( Question question ) {

        jSpinner = new JSpinner( new SpinnerNumberModel( new Double( question.string() ), null, null, 0.01 ) );

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) jSpinner.getEditor();

        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 2 );
        format.setMinimumFractionDigits( 2 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        
        Dimension d = jSpinner.getPreferredSize();
        d.width = 185;
        jSpinner.setPreferredSize( d );

        jSpinner.addChangeListener( e -> {

            JSpinner source = (JSpinner) e.getSource();
            String current = source.getModel().getValue().toString();
            String dec = new BigDecimal( current ).setScale( 2, RoundingMode.HALF_EVEN ).toString();
            questionsInterpretor().assign( question, Value.of( Type.Money, dec ) );

        } );
    }
}
