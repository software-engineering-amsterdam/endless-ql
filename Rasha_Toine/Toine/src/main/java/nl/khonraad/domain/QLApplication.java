package nl.khonraad.domain;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.khonraad.utils.AbstractParserFactory;
import nl.khonraad.visitors.InterpretingVisitor;

public class QLApplication {

	String testData = "form Box1HouseOwning {																			"
			+ "   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"
			+ "   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"
			+ "   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"
			+ "																								"
			+ "	if (hasSoldHouse) {																			"
			+ "		sellingPrice: \"Price the house was sold for:\" money										"
			+ "		privateDebt: \"Private debts for the sold house:\" money									"
			+ "  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01 )				"
			+ "  		testDate: \"Testdate:\" date (01/01/1970 + 3 )				"
			+ "  		testString: \"testString:\" string (\"abc\"  + \"ABC\")				"
			+ "  	}																							"
			+ "}																								";

	final InterpretingVisitor interpretingVisitor = new InterpretingVisitor();
	final ParseTree parseTree = AbstractParserFactory.parseDataForTest( testData ).form();
	final JPanel mainPanel = new JPanel();

	public static void main( String[] args ) throws RecognitionException, IOException {

		new QLApplication();
	}


	public QLApplication() throws RecognitionException, IOException {

		JFrame guiFrame = new JFrame();

		initializeGuiFrame( guiFrame );

		mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );

		guiFrame.add( mainPanel, BorderLayout.NORTH );

		show( mainPanel );

		guiFrame.setVisible( true );

	}

	private void show( JPanel mainPanel ) throws RecognitionException, IOException {

		mainPanel.removeAll();

		interpretingVisitor.visit( parseTree );

		// interpretingVisitor.answerableQuestions.get( "hasSoldHouse"
		// ).parseThenSetValue( "True" );
		//
		// interpretingVisitor.visit( parseTree );
		//
		// interpretingVisitor.answerableQuestions.get( "sellingPrice"
		// ).parseThenSetValue( "100.03" );
		// interpretingVisitor.answerableQuestions.get( "privateDebt"
		// ).parseThenSetValue( "25.00" );
		//
		// interpretingVisitor.visit( parseTree );

		for (String s : interpretingVisitor.declaredQuestionTypes) {

			if (interpretingVisitor.answerableQuestions.containsKey( s )) {
				AnswerableQuestion answerableQuestion = interpretingVisitor.answerableQuestions.get( s );
				mainPanel.add( makeQuestion( s, true, answerableQuestion.getValue().getType(),
						answerableQuestion.getLabel(), answerableQuestion.getValue().getText() ) );
			}
			if (interpretingVisitor.computedQuestions.containsKey( s )) {
				ComputedQuestion computedQuestion = interpretingVisitor.computedQuestions.get( s );
				mainPanel.add( makeQuestion( null, false, computedQuestion.getValue().getType(),
						computedQuestion.getLabel(), computedQuestion.getValue().getText() ) );
			}

		}

		mainPanel.add( new JLabel( new Date().toString() ) );

		mainPanel.updateUI();
		mainPanel.repaint();
		// guiFrame.invalidate();

	}

	private void initializeGuiFrame( JFrame guiFrame ) {

		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		guiFrame.setTitle( "GUI" );
		guiFrame.setSize( 700, 550 );

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo( null );
	}

	private JPanel makeQuestion( String id, boolean answerable, Type type, String label, String text ) {

		JPanel container = new JPanel();

		JLabel leftComponent = new JLabel( label );
		container.add( leftComponent );

		switch ( type ) {

			case Boolean: {
				container.add( answerable ? createBooleanBox( id, text ) : boldText( text ) );
				break;
			}
			case Date: {
				container.add( answerable ? createDateBox( id, text ) : boldText( text ) );
				break;
			}
			case Integer: {
				container.add( answerable ? createIntegerBox( id, text ) : boldText( text ) );
				break;
			}
			case Money: {
				container.add( answerable ? createMoneyBox( id, text ) : boldText( text ) );
				break;
			}
			case String:
				container.add( answerable ? createStringBox( id, text ) : boldText( text ) );
				break;

		}

		return container;
	}

	private JLabel boldText( String text ) {
		JLabel jLabel = new JLabel( text );
		jLabel.setFont( new Font( "Courier", Font.BOLD, 16 ) );
		return jLabel;
	}

	private JSpinner createMoneyBox( String id, String text ) {

		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Double current = new Double( text );
		Double step = new Double( 0.05 );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JSpinner createIntegerBox( String id, String text ) {

		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Integer current = new Integer( text );
		Integer step = new Integer( "1" );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JTextField createDateBox( String id, String text ) {

		JTextField rightComponent = new JTextField( text, 10 );
		return rightComponent;
	}

	private JTextField createStringBox( String id, String text ) {

		JTextField rightComponent = new JTextField( text, 40 );
		rightComponent.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		return rightComponent;
	}

	@SuppressWarnings("unchecked")
	private JComboBox<String> createBooleanBox( String id, String text ) {

		String[] options = { "True", "False" };
		JComboBox<String> rightComponent = new JComboBox<String>( options );
		rightComponent.setSelectedItem( text );
		rightComponent.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String current = (String) combo.getSelectedItem();
				AnswerableQuestion answerableQuestion = interpretingVisitor.answerableQuestions.get( id );
				answerableQuestion.parseThenSetValue( current );
				try {
					show( mainPanel );
				} catch (RecognitionException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} );
		return rightComponent;
	}
}