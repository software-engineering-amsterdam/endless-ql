package nl.khonraad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.khonraad.domain.Question;
import nl.khonraad.domain.Question.BehaviouralType;
import nl.khonraad.domain.Type;
import nl.khonraad.utils.AbstractParserFactory;

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

	final QLVisitor interpretingVisitor = new QLVisitor();
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

		for (Question question : interpretingVisitor.listQuestions()) {
			mainPanel.add( makeQuestion( question.getIdentifier(), question.getBehaviouralType(),
					question.getValue().getType(), question.getLabel(), question.getValue().getText() ) );

		}

		mainPanel.add( new JLabel( new Date().toString() ) );
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
		parent.add( component);
		return parent;
	}

	private JPanel makeQuestion( String identifier, BehaviouralType behaviouralType, Type type, String label, String text ) {

		JPanel container = new JPanel();

		JLabel leftComponent = new JLabel( label );
		container.add( leftComponent, BorderLayout.WEST  );

		if (behaviouralType == BehaviouralType.COMPUTED) {

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

		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Double current = new Double( text );
		Double step = new Double( 0.05 );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JSpinner createIntegerBox( String identifier, String text ) {

		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Integer current = new Integer( text );
		Integer step = new Integer( "1" );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JTextField createDateBox( String identifier, String text ) {

		JTextField rightComponent = new JTextField( text, 10 );
		return rightComponent;
	}

	private JTextField createStringBox( String identifier, String text ) {

		JTextField rightComponent = new JTextField( text, 40 );
		rightComponent.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		return rightComponent;
	}

	@SuppressWarnings("unchecked")
	private JComboBox<String> createBooleanBox( String identifier, String text ) {

		String[] options = { "True", "False" };
		JComboBox<String> rightComponent = new JComboBox<String>( options );
		rightComponent.setSelectedItem( text );
		rightComponent.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent e ) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String current = (String) combo.getSelectedItem();

				interpretingVisitor.findQuestion( BehaviouralType.ANSWERABLE, identifier ).get().parseThenSetValue( current );
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