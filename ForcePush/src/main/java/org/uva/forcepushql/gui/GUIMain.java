package org.uva.forcepushql.gui;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;


public class GUIMain
{
	 

	public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(new Runnable()
        	{
	            public void run() 
	            {
	            	Create_GUI_Frame();
	            }
        });
    }
	
	private static void Create_GUI_Frame() 
	{
	JFrame guiFrame = new JFrame("MainFrame");
	//RadioButtons radio = new RadioButtons();

	Radio radioQuestion1 = new Radio("Did you sell a house in 2010?", "boolean", "hasSoldHouse");
    Radio radioQuestion2 = new Radio("Did you buy a house in 2010?", "boolean", "hasBoughtHouse");
    Radio radioQuestion3 = new Radio("Did you enter a loan?", "boolean", "hasMaintLoan");
    Textbox textBoxQuestion1 = new Textbox("What was the selling price?", "money", "sellingPrice");
    Textbox textBoxQuestion2 = new Textbox("Private debts for the sold house:", "money", "privateDebt");
    Textbox textboxQuestion3 = new Textbox("Value Residue:", "money", "valueResidue");

	/*JComponent newContentPane = radio;
	newContentPane.setOpaque(true);
	guiFrame.setContentPane(newContentPane);*/

	guiFrame.setLocationRelativeTo(null);

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	panel1.setPreferredSize(new Dimension(300,90));
	//panel1.setBackground(Color.PINK);
	panel2.setPreferredSize(new Dimension(300, 100));
	//panel2.setBackground(Color.RED);


	JLabel label1 = new JLabel(radioQuestion1.writtenQuestion(), JLabel.CENTER);
	JLabel label2 = new JLabel(radioQuestion2.writtenQuestion(), JLabel.CENTER);
	JLabel label3 = new JLabel(radioQuestion3.writtenQuestion(), JLabel.CENTER);
    JLabel label4 = new JLabel(textBoxQuestion1.writtenQuestion(), JLabel.CENTER);
    JLabel label5 = new JLabel(textBoxQuestion2.writtenQuestion(), JLabel.CENTER);
    JLabel label6 = new JLabel(textboxQuestion3.writtenQuestion(), JLabel.CENTER);


	JTextField text1 = new JTextField(5);
	JTextField text2 = new JTextField(5);
    JTextField text3 = new JTextField(5);
    text3.setEnabled(false);
    text3.setDisabledTextColor(Color.BLACK);

    JRadioButton radio1 = new JRadioButton();
    radio1.setText("Yes");
    JRadioButton radio2 = new JRadioButton();
    radio2.setText("No");

    JRadioButton radio3 = new JRadioButton();
    radio3.setText("Yes");
    JRadioButton radio4 = new JRadioButton();
    radio4.setText("No");

    JRadioButton radio5 = new JRadioButton();
    radio5.setText("Yes");
    JRadioButton radio6 = new JRadioButton();
    radio6.setText("No");

    ButtonGroup buttonGroup1 = new ButtonGroup();
    buttonGroup1.add(radio1);
    buttonGroup1.add(radio2);

    ButtonGroup buttonGroup2 = new ButtonGroup();
    buttonGroup2.add(radio3);
    buttonGroup2.add(radio4);

    ButtonGroup buttonGroup3 = new ButtonGroup();
    buttonGroup3.add(radio5);
    buttonGroup3.add(radio6);

    panel1.add(label1);
    panel1.add(radio1);
    panel1.add(radio2);

    panel1.add(label2);
    panel1.add(radio3);
    panel1.add(radio4);

    panel1.add(label3);
    panel1.add(radio5);
    panel1.add(radio6);

    panel2.add(label4);
    panel2.add(text1);

    panel2.add(label5);
    panel2.add(text2);

    panel2.add(label6);
    panel2.add(text3);

    final int[] values = new int[2];


    panel2.setVisible(false);

        ActionListener listen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) e.getSource();

                if (radioButton.isSelected() && (radioButton.getText() == "Yes")) {
                    radioQuestion1.givenAnswer(true);
                    System.out.println(radioQuestion1.answerValue());

                    panel2.setVisible(true);

                }

                else if(radioButton.isSelected() && (radioButton.getText() == "No")){
                    radioQuestion1.givenAnswer(false);
                    System.out.println(radioQuestion1.answerValue());

                    panel2.setVisible(false);
                }

            }
        }
    };

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = text1.getText();
                values[0] = Integer.parseInt(textFieldValue);
                System.out.println(values[0]);
            }
        };

        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = text2.getText();
                values[1] = Integer.parseInt(textFieldValue);
                System.out.println(values[1]);

                if(values[0] != 0 && values[1] != 0) {
                    int val = values[0] - values[1];
                    text3.setText(Integer.toString(val));
                }
            }
        };

       /* ActionListener listener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(values);
                if(values[0] != 0 && values[1] != 0) {
                    int val = values[0] - values[1];
                    text3.setText(Integer.toString(val));
                }
            }
        };*/

    radio1.addActionListener(listen);
    radio2.addActionListener(listen);

    text1.addActionListener(listener1);
    text2.addActionListener(listener2);
    //text3.addActionListener(listener3);

    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("Questionnaire");
    guiFrame.setSize(720,720);

    guiFrame.setLayout(null);

    guiFrame.add(panel1);
    guiFrame.add(panel2);

    Insets insets = guiFrame.getInsets();
    Dimension size = panel1.preferredSize();
    panel1.setBounds(220 + insets.left, 5 + insets.top, size.width, size.height);
    size = panel2.preferredSize();
    panel2.setBounds(220 + insets.left, 100 + insets.top, 20 + size.width, size.height);

	guiFrame.setVisible(true);


	}

	
}

