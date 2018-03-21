package org.uva.forcepushql.gui;
import org.uva.forcepushql.questions.Question;
import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
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

	Radio radioQuestion1 = new Radio("Did you sell a house in 2010?", "boolean", "hasSoldHouse");
    Radio radioQuestion2 = new Radio("Did you buy a house in 2010?", "boolean", "hasBoughtHouse");
    Radio radioQuestion3 = new Radio("Did you enter a loan?", "boolean", "hasMaintLoan");
    Textbox textBoxQuestion1 = new Textbox("What was the selling price?", "money", "sellingPrice");
    Textbox textBoxQuestion2 = new Textbox("Private debts for the sold house:", "money", "privateDebt");
    Textbox textBoxQuestion3 = new Textbox("Value Residue:", "money", "valueResidue");

	guiFrame.setLocationRelativeTo(null);

	LinkedList<Question> q1 = new LinkedList<Question>();
	q1.add(radioQuestion1);
	q1.add(radioQuestion2);
    q1.add(radioQuestion3);
	LinkedList<Question> q2 = new LinkedList<Question>();
	q2.add(textBoxQuestion1);
	q2.add(textBoxQuestion2);
    q2.add(textBoxQuestion3);

	JPanelGUI jPanelGUI1 = new JPanelGUI();
    JPanelGUI jPanelGUI2 = new JPanelGUI();

	jPanelGUI1.createPanel(q1, 0);
	jPanelGUI2.createPanel(q2, jPanelGUI1.getHeight());

    JPanel panel1 = jPanelGUI1.getPanel();
    JPanel panel2 = jPanelGUI2.getPanel();

        final int[] values = new int[2];


    panel2.setVisible(false);

        ActionListener listen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) e.getSource();

                if (radioButton.isSelected() && (radioButton.getText() == "Yes")) {
                    radioQuestion1.givenAnswer(true);

                    panel2.setVisible(true);

                }

                else if(radioButton.isSelected() && (radioButton.getText() == "No")){
                    radioQuestion1.givenAnswer(false);

                    panel2.setVisible(false);
                }

            }
        }
    };

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JTextField) {
                    JTextField textField = (JTextField) e.getSource();

                    String textFieldValue = textField.getText();
                    values[0] = Integer.parseInt(textFieldValue);
                }
            }
        };

        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JTextField) {
                    JTextField textField = (JTextField) e.getSource();
                    String textFieldValue = textField.getText();
                    values[1] = Integer.parseInt(textFieldValue);

                    if (values[0] != 0 && values[1] != 0) {
                        int val = values[0] - values[1];
                        jPanelGUI2.setText(Integer.toString(val), textBoxQuestion3.answerNameValue());
                    }
                }
            }
        };



    jPanelGUI1.addActionListener(radioQuestion1.answerNameValue(), listen);

    jPanelGUI2.addActionListener(textBoxQuestion1.answerNameValue(), listener1);
    jPanelGUI2.addActionListener(textBoxQuestion2.answerNameValue(), listener2);
    jPanelGUI2.setEnable(false, textBoxQuestion3.answerNameValue());


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

