package nl.khonraad.gui.interactions;

import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import nl.khonraad.ql.language.Question;
import nl.khonraad.qls.language.StyleElement;
import nl.khonraad.cdi.accessors.QuestionsInterpretorAccessor;
import nl.khonraad.ql.algebra.values.Value;

public class BooleanWidget implements QuestionsInterpretorAccessor {

    private JComboBox<String> jComboBox;

    JComponent jComponent() {

        return jComboBox;
    }

    class DisplayedValues {

        private String[] displayedStrings = { Value.FalseString, Value.TrueString };

        DisplayedValues( Optional<StyleElement> styleElement ) {

            if ( styleElement.isPresent() ) {

                displayedStrings[0] = styleElement.get().styledFalseString();
                displayedStrings[1] = styleElement.get().styledTrueString();
            }
        }

        String[] arrayOfStrings() {
            return displayedStrings;
        }

        String display( Value value ) {
            if ( value.equals( Value.False ) ) {
                return displayedStrings[0];
            }
            return displayedStrings[1];
        }

        public Value value( String string ) {
            if ( displayedStrings[0].equals( string ) ) {
                return Value.False;
            }
            return Value.True;
        }

    }

    public BooleanWidget( Question question, Optional<StyleElement> styleElement ) {

        DisplayedValues displayedValues = new DisplayedValues( styleElement );
        jComboBox = new JComboBox<>( displayedValues.arrayOfStrings() );
        Value value = question.value();
        String selectedAsInitial = displayedValues.display( value );
        jComboBox.setSelectedItem( selectedAsInitial );

        jComboBox.addActionListener( e -> {

            @SuppressWarnings( "unchecked" )
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedString = (String) combo.getSelectedItem();
            Value choiceMade = displayedValues.value( selectedString );
            questionsInterpretor().assign( question, choiceMade );
        } );
    }
}
