package nl.khonraad.domain;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class QLApplication {

	public static void main( String[] args ) {

		new QLApplication();

	}

	public QLApplication() {
		
		
		JFrame guiFrame = new JFrame();

		initializeGuiFrame( guiFrame );

		JPanel mainPanel = new JPanel();

		mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );

		guiFrame.add( mainPanel, BorderLayout.NORTH );

		mainPanel.add( makeQuestion( true, Type.Boolean, "Did you sell?", "True" ) );
		mainPanel.add( makeQuestion( true, Type.Boolean, "Did you REALLY sell?", "False" ) );
		mainPanel.add( makeQuestion( true, Type.Date, "When did you?", "01/01/2000" ) );
		mainPanel.add( makeQuestion( true, Type.Money, "The cost?", "100.21" ) );
		mainPanel.add( makeQuestion( true, Type.Integer, "When did you?", "42" ) );
		mainPanel.add( makeQuestion( true, Type.String, "Why you?", "testxtje" ) );

		guiFrame.setVisible( true );

	}

	private void initializeGuiFrame( JFrame guiFrame ) {
		
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		guiFrame.setTitle( "GUI" );
		guiFrame.setSize( 700, 250 );

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo( null );
	}

	private JPanel makeQuestion( boolean answerable, Type type, String label, String text ) {

		JPanel container = new JPanel();

		JLabel leftComponent = new JLabel( label );
		container.add( leftComponent );

		switch ( type ) {
			
			case Boolean: {
				container.add( createBooleanBox(text) );
				break;
			}
			case Date: {
				container.add( createDateBox( text ) );
				break;
			}
			case Integer: {
				container.add( createIntegerBox( text ) );
				break;
			}
			case Money: {
				container.add( createMoneyBox( text ) );
				break;
			}

			default: {
				container.add( createStringBox( text ) );
			}

		}

		return container;
	}

	private JSpinner createMoneyBox( String text ) {
		
		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Double current = new Double( text );
		Double step = new Double( 0.05 );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JSpinner createIntegerBox( String text ) {
		
		JSpinner m_numberSpinner;
		SpinnerNumberModel m_numberSpinnerModel;
		Integer current = new Integer( text );
		Integer step = new Integer( "1" );
		m_numberSpinnerModel = new SpinnerNumberModel( current, null, null, step );
		m_numberSpinner = new JSpinner( m_numberSpinnerModel );
		return m_numberSpinner;

	}

	private JTextField createDateBox( String text ) {
		
		JTextField rightComponent = new JTextField( text, 10 );
		return rightComponent;
	}

	private JTextField createStringBox( String text ) {
		
		JTextField rightComponent = new JTextField( text, 40 );
		return rightComponent;
	}

	private JComboBox<String> createBooleanBox(String text) {
		
		String[] options = { "Choose one", "Yes", "No" };
		JComboBox<String> rightComponent = new JComboBox<String>( options );
		rightComponent.setSelectedItem( "True".equals( text ) ? "Yes" : "Choose one" );
		return rightComponent;
	}

}