package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Radio;

import javax.swing.*;

public class QL {
    private JPanel mainPanel;
    private JPanel panelQuestion1;
    private JLabel labelQuestion1;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;


    public static void main (String args[]){

        JFrame frame = new JFrame("Questionnaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);

        Radio radioQuestion1 = new Radio("Did you sell a house in 2010?", "boolean", "hasSoldHouse");
        Radio radioQuestion2 = new Radio("Did you sell the house for more than 11000€?", "boolean", "moneyGained");



        frame.add(new QL().mainPanel);


        frame.setVisible(true);

    }
}
