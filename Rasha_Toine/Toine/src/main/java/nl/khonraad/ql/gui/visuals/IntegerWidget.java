package nl.khonraad.ql.gui.visuals;

import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.cdi.InterpretorAccessor;
import nl.khonraad.ql.language.Question;

public class IntegerWidget implements InterpretorAccessor {

    JSpinner jSpinner;

    public IntegerWidget( Question question ) {

        jSpinner = new JSpinner( new SpinnerNumberModel( new Integer( question.string() ), null, null, 1 ) );

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) jSpinner.getEditor();

        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 0 );
        format.setMinimumFractionDigits( 0 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        Dimension d = jSpinner.getPreferredSize();
        d.width = 185;
        jSpinner.setPreferredSize( d );

        jSpinner.addChangeListener( e -> {

            JSpinner source = (JSpinner) e.getSource();

            String current = source.getModel().getValue().toString();

            interpretor().assign( question, Value.typed( Type.Integer, current ) );
        } );
    }
}
