package nl.khonraad.ql.gui.visuals;

import javax.swing.JComboBox;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdi.QuestionnaireAccessor;

public class BooleanWidget implements QuestionnaireAccessor {

    JComboBox<String> jComboBox;

    public BooleanWidget( Question question ) {

        jComboBox = new JComboBox<>( new String[] { new Value( false ).string(), new Value( true ).string() } );

        jComboBox.setSelectedItem( question.value().string() );

        jComboBox.addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            String current = (String) combo.getSelectedItem();

            questionnaire().storeAnswer( question.identifier(), new Value( Type.Boolean, current ) );

        } );
    }
}
