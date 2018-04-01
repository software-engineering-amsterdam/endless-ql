package nl.khonraad.ql.gui.visuals;

import javax.swing.JComboBox;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdi.QuestionnaireAccessor;
import nl.khonraad.qls.ast.data.StyleElement;

public class BooleanWidget implements QuestionnaireAccessor {

    JComboBox<String> jComboBox;

    String            displayForFalse = new Value( false ).string();
    String            displayForTrue  = new Value( true ).string();

    public BooleanWidget( Question question, StyleElement styleElement ) {

        /*
         * TODO styleElement has to be linked to question! Decorator?
         */
        if ( styleElement != null ) {

            jComboBox = new JComboBox<>( styleElement.displayValues() );
        }

        String selected = question.value().equals( new Value( true ) ) ? displayForTrue : displayForFalse;

        jComboBox.setSelectedItem( selected );

        jComboBox.addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            String current = (String) combo.getSelectedItem();

            String result = displayForFalse.equals( current ) ? new Value( false ).string()
                    : new Value( true ).string();

            questionnaire().storeAnswer( question.identifier(), new Value( Type.Boolean, result ) );
        } );
    }
}
