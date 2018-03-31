package nl.khonraad.ql.gui.visuals;

import javax.swing.JComboBox;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question;
import nl.khonraad.ql.cdisupport.QuestionnaireAccessor;

@SuppressWarnings( "serial" ) 

public class BooleanWidget extends JComboBox<String> implements QuestionnaireAccessor {

    public BooleanWidget( Question question ) {

        super( new String[] { new Value( false ).string(), new Value( true ).string() } );

        setSelectedItem( question.value().string() );

        addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            String current = (String) combo.getSelectedItem();

            questionnaire().storeAnswer( question.identifier(), new Value( Type.Boolean, current ) );

        } );
    }
}
