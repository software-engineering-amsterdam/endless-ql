package nl.khonraad.ql.gui.visuals;

import javax.swing.JComboBox;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdi.QuestionnaireAccessor;
import nl.khonraad.qls.ast.data.StyleElement;

public class BooleanWidget implements QuestionnaireAccessor {

    JComboBox<String> jComboBox;

    String            no  = new Value( false ).string();
    String            yes = new Value( true ).string();

    public BooleanWidget( Question question, StyleElement styleElement ) {

        if ( styleElement != null ) {

            no = styleElement.getF();
            yes = styleElement.getT();

            jComboBox = new JComboBox<>( new String[] { no, yes } );
        }

        String selected = question.value().equals( new Value( true ) ) ? yes : no;
        
        jComboBox.setSelectedItem( selected );

        jComboBox.addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            String current = (String) combo.getSelectedItem();

            String result = no.equals( current ) ? new Value( false ).string() : new Value( true ).string();

            questionnaire().storeAnswer( question.identifier(), new Value( Type.Boolean, result ) );

        } );
    }
}
