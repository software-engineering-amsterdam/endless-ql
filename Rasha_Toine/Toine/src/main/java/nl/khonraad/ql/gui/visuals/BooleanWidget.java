package nl.khonraad.ql.gui.visuals;

import java.util.Optional;

import javax.swing.JComboBox;

import nl.khonraad.ql.cdi.QuestionnaireAccessor;
import nl.khonraad.ql.domain.Question;
import nl.khonraad.qls.ast.data.StyleElement;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class BooleanWidget implements QuestionnaireAccessor {

    JComboBox<String> jComboBox;

    public BooleanWidget( Question question, Optional<StyleElement> styleElement ) {

        /*
         * TODO styleElement has to be linked to question! Decorator?
         */

        StyleElement justFalseOrTrue = new StyleElement( Type.Boolean, Value.False.string(), Value.True.string() );

        String[] initializer = { styleElement.orElse( justFalseOrTrue ).falseString(),
                styleElement.orElse( justFalseOrTrue ).trueString(), };

        jComboBox = new JComboBox<>( initializer );

        String selected = question.value().equals( Value.False ) ? initializer[0] : initializer[1];

        jComboBox.setSelectedItem( selected );

        jComboBox.addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();

            String current = (String) combo.getSelectedItem();

            String result = initializer[0].equals( current ) ? Value.False.string() : Value.True.string();

            questionnaire().storeAnswer( question.identifier(), Value.typed( Type.Boolean, result ) );
        } );
    }
}
