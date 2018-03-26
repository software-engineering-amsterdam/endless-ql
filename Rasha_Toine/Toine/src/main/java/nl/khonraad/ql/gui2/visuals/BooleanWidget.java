package nl.khonraad.ql.gui2.visuals;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.dynamics.Question;
import nl.khonraad.ql.dynamics.Questionnaire;
import nl.khonraad.ql.gui2.QLInterpretor;

@SuppressWarnings("serial")
public class BooleanWidget extends JComboBox<String> {

    public BooleanWidget( JPanel mainPanel, Question question, Questionnaire questionnaire) {

        super(new String[]{Value.FALSE.getText(),Value.TRUE.getText() });
        
        
        setSelectedItem( question.getValue().getText());

        addActionListener( e -> {

            @SuppressWarnings("unchecked")
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            
            String current = (String) combo.getSelectedItem();

            questionnaire.storeAnswer( question.getIdentifier(), new Value( Type.Boolean, current ) );

            QLInterpretor.visualizeQuestionnaire( questionnaire, mainPanel );
        } );
    }
}
