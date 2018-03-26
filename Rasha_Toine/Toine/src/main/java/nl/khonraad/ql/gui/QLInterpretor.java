package nl.khonraad.ql.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.dynamics.Question;
import nl.khonraad.ql.dynamics.Question.BehaviouralType;
import nl.khonraad.ql.gui.visuals.BooleanWidget;
import nl.khonraad.ql.gui.visuals.ComputedValue;
import nl.khonraad.ql.gui.visuals.DateWidget;
import nl.khonraad.ql.gui.visuals.IntegerWidget;
import nl.khonraad.ql.gui.visuals.MoneyWidget;
import nl.khonraad.ql.gui.visuals.StringWidget;
import nl.khonraad.ql.dynamics.Questionnaire;

public class QLInterpretor {

    public static void main( String[] args ) throws IOException {

        new QLInterpretor();
    }

    public QLInterpretor() throws IOException {

        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        guiFrame.setTitle( getClass().getSimpleName() );
        guiFrame.setSize( 800, 700 );
        guiFrame.setLocationRelativeTo( null );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new GridLayout( 0, 2 ) );

        guiFrame.add( mainPanel, BorderLayout.NORTH );

        InputStream stream = getClass().getResourceAsStream( "/Gui" );
        
        final Questionnaire questionnaire = new Questionnaire( stream );
        
        visualizeQuestionnaire( questionnaire, mainPanel );

        guiFrame.setVisible( true );

    }

    public static void visualizeQuestionnaire( Questionnaire questionnaire, JPanel mainPanel ) {

        mainPanel.removeAll();

        questionnaire.visit();

        for ( Question question : questionnaire.getQuestionList() ) {
           
            mainPanel.add( new JLabel( question.getLabel() ) );
            mainPanel.add( visualizeQuestion( mainPanel, question, questionnaire ) );
        }

        mainPanel.validate();
        mainPanel.updateUI();
        mainPanel.repaint();

    }

    private static JPanel addToParent( JPanel parent, JComponent component ) {
        JPanel container = new JPanel();
        container.add( component, new GridLayout( 0, 2 ) );
        parent.add( container );
        return parent;
    }

    private static JPanel visualizeQuestion( JPanel mainPanel, Question question, Questionnaire questionnaire ) {

        BehaviouralType behaviouralType = question.getBehaviouralType();

        Type type = question.getValue().getType();

        JPanel container = new JPanel();

        if ( behaviouralType == BehaviouralType.COMPUTED ) {

            return addToParent( container, new ComputedValue( mainPanel, question, questionnaire ) );
        
        } else {

            switch ( type ) {

                case Boolean:
                    return addToParent( container, new BooleanWidget( mainPanel, question, questionnaire ) );

                case Date:
                    return addToParent( container, new DateWidget( mainPanel, question, questionnaire ) );

                case Integer:
                    return addToParent( container, new IntegerWidget( mainPanel, question, questionnaire ) );

                case Money:
                    return addToParent( container, new MoneyWidget( mainPanel, question, questionnaire ) );

                case String:
                    return addToParent( container, new StringWidget( mainPanel, question, questionnaire ) );
            }
        }
        throw new RuntimeException( "Do not know how to diplay type: " + type );

    }

//    JFormattedTextField getTextField( JSpinner spinner ) {
//        JComponent editor = spinner.getEditor();
//        if ( editor instanceof JSpinner.DefaultEditor ) {
//            return ((JSpinner.DefaultEditor) editor).getTextField();
//        } else {
//            System.err.println( "Unexpected editor type: " + spinner.getEditor().getClass()
//                    + " isn't a descendant of DefaultEditor" );
//            return null;
//        }
//    }

}