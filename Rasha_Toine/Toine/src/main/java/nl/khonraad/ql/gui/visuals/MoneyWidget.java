package nl.khonraad.ql.gui.visuals;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdi.QuestionnaireAccessor;

@SuppressWarnings("serial") 

public class MoneyWidget extends JSpinner implements QuestionnaireAccessor {

    public MoneyWidget(Question question) {

        super( new SpinnerNumberModel( new Double( question.string() ), null, null, 0.01 ) );

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) getEditor();

        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 2 );
        format.setMinimumFractionDigits( 2 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        Dimension d = getPreferredSize();
        d.width = 185;
        setPreferredSize( d );

        addChangeListener( e -> {

            JSpinner source = (JSpinner) e.getSource();

            String current = source.getModel().getValue().toString();

            String dec = new BigDecimal( current ).setScale(2, RoundingMode.HALF_EVEN).toString();
            
            questionnaire().storeAnswer( question.identifier(), new Value( Type.Money, dec ) );

        } );
    }
}
