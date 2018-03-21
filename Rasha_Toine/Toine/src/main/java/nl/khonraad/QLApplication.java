package nl.khonraad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.antlr.v4.runtime.RecognitionException;

import nl.khonraad.ql.domain.Question;
import nl.khonraad.ql.domain.Question.BehaviouralType;
import nl.khonraad.ql.domain.Questionnaire;
import nl.khonraad.ql.domain.Type;
import nl.khonraad.ql.domain.Value;

public class QLApplication {

    String              testData      = "                                                                  "
            + "form     Box1HouseOwning {                                                                  "
            + "     hasSoldHouse:   \"Did you sell a house in 2010?\" boolean                              "
            + "     hasBoughtHouse: \"Did you by a house in 2010?\" boolean                                "
            + "     hasMaintLoan:   \"Did you enter a loan for maintenance/reconstruction?\"  boolean      "
            + "                                                                                            "
            + "        if (hasSoldHouse) {                                                                 "
            + "           sellingPrice: \"Price the house was sold for:\" money                            "
            + "           privateDebt: \"Private debts for the sold house:\" money                         "
            + "      valueResidue: \"Value residue:\" money (sellingPrice - privateDebt )                  "
            + "     }                                                                                      "
            + "}                                                                                           ";

    final Questionnaire questionnaire = new Questionnaire( testData );

    final JPanel        mainPanel     = new JPanel();

    public static void main( String[] args ) throws IOException {

        new QLApplication();
    }

    public QLApplication() throws IOException {

        JFrame guiFrame = new JFrame();

        initializeGuiFrame( guiFrame );

        mainPanel.setLayout( new GridLayout( 0, 2 ) );

        guiFrame.add( mainPanel, BorderLayout.NORTH );

        visualizeQuestionnaire( mainPanel );

        guiFrame.setVisible( true );

    }

    private void visualizeQuestionnaire( JPanel mainPanel ) {

        mainPanel.removeAll();

        questionnaire.visit();

        for ( Question question : questionnaire.getQuestionList() ) {

            JLabel jLabel = new JLabel( question.getLabel() );
            mainPanel.add( jLabel );

            mainPanel.add( visualizeQuestion( question ) );
        }

        mainPanel.validate();
        mainPanel.updateUI();
        mainPanel.repaint();

    }

    private void initializeGuiFrame( JFrame guiFrame ) {

        guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        guiFrame.setTitle( "GUI" );
        guiFrame.setSize( 700, 550 );

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo( null );
    }

    private JPanel addToParent( JPanel parent, JComponent component ) {
        JPanel container = new JPanel();
        container.add( component, new GridLayout( 0, 2 ) );
        parent.add( container );
        return parent;
    }

    private JPanel visualizeQuestion( Question question ) {

        String identifier = question.getIdentifier();
        BehaviouralType behaviouralType = question.getBehaviouralType();
        Type type = question.getValue().getType();
        String text = question.getValue().getText();

        JPanel container = new JPanel();

        if ( behaviouralType == BehaviouralType.COMPUTED ) {

            return addToParent( container, boldText( text ) );
        }

        switch ( type ) {

            case Boolean:
                return addToParent( container, createBooleanBox( identifier, text ) );

            case Date:
                return addToParent( container, createDateBox( identifier, text ) );

            case Integer:
                return addToParent( container, createIntegerBox( identifier, text ) );

            case Money:
                return addToParent( container, createMoneyBox( identifier, text ) );

            case String:
                return addToParent( container, createStringBox( identifier, text ) );
        }
        throw new RuntimeException( "Do not know how to diplay type: " + type );

    }

    private JLabel boldText( String text ) {
        JLabel jLabel = new JLabel( text );
        jLabel.setFont( new Font( "Courier", Font.BOLD, 16 ) );
        return jLabel;
    }

    private JSpinner createMoneyBox( String identifier, String text ) {

        Double min = null;
        Double current = new Double( text );
        Double max = null;
        Double stepSize = 0.01;

        SpinnerNumberModel model = new SpinnerNumberModel( current, min, max, stepSize );
        JSpinner spinner = new JSpinner( model );
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 2 );
        format.setMinimumFractionDigits( 2 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        Dimension d = spinner.getPreferredSize();
        d.width = 85;
        spinner.setPreferredSize( d );

        addFocusListenerForSpinner( identifier, spinner, Type.Money );
        return spinner;

    }

    private JSpinner createIntegerBox( String identifier, String text ) {

        Integer min = null;
        Integer current = new Integer( text );
        Integer max = null;
        Integer stepSize = 1;

        SpinnerNumberModel model = new SpinnerNumberModel( current, min, max, stepSize );
        JSpinner spinner = new JSpinner( model );
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMaximumFractionDigits( 0 );
        format.setMinimumFractionDigits( 0 );
        editor.getTextField().setHorizontalAlignment( SwingConstants.RIGHT );
        Dimension d = spinner.getPreferredSize();
        d.width = 85;
        spinner.setPreferredSize( d );

        addFocusListenerForSpinner( identifier, spinner, Type.Integer );
        return spinner;

    }

    private JTextField createDateBox( String identifier, String text ) {

        JTextField component = new JTextField( text, 10 );

        addFocusListenerForJTextField( identifier, component, Type.Date );

        return component;
    }

    private JTextField createStringBox( String identifier, String text ) {

        JTextField component = new JTextField( text, 40 );

        addFocusListenerForJTextField( identifier, component, Type.String );

        return component;
    }

    private void addFocusListenerForSpinner( String identifier, JSpinner component, Type type ) {

        component.addChangeListener( e -> {

            JSpinner spinner = (JSpinner) e.getSource();

            String current = spinner.getModel().getValue().toString();

            questionnaire.storeAnswer( identifier, new Value( type, current ) );
            visualizeQuestionnaire( mainPanel );
        } );
    }

    private void addFocusListenerForJTextField( String identifier, JTextField component, Type type ) {
        component.addFocusListener( new FocusListener() {

            @Override
            public void focusLost( FocusEvent e ) {

                JTextField textField = (JTextField) e.getSource();
                String current = textField.getText();

                questionnaire.storeAnswer( identifier, new Value( type, current ) );
                visualizeQuestionnaire( mainPanel );
            }

            @Override
            public void focusGained( FocusEvent e ) {
                // TODO Auto-generated method stub

            }
        } );
    }

    @SuppressWarnings("unchecked")
    private JComboBox<String> createBooleanBox( String identifier, String text ) {

        String[] options = { Value.FALSE.getText(), Value.TRUE.getText() };
        JComboBox<String> component = new JComboBox<>( options );

        component.setSelectedItem( text );

        component.addActionListener( e -> {

            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String current = (String) combo.getSelectedItem();

            questionnaire.storeAnswer( identifier, new Value( Type.Boolean, current ) );

            visualizeQuestionnaire( mainPanel );
        } );
        return component;
    }

    JFormattedTextField getTextField( JSpinner spinner ) {
        JComponent editor = spinner.getEditor();
        if ( editor instanceof JSpinner.DefaultEditor ) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println( "Unexpected editor type: " + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor" );
            return null;
        }
    }
}