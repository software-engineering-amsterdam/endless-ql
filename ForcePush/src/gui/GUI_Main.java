package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI_Main {
	 
	 //Note: Typically the main method will be in a
	 //separate class. As this is a simple one class
	 //example it's all in the one class.
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	GUI_Frame();
            }
        });
    }
	 public static void GUI_Frame() {
	 JFrame guiFrame = new JFrame("MainFrame");
	 
	 //make sure the program exits when the frame closes
	 guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 guiFrame.setTitle("Questionnaire");
	 guiFrame.setSize(1280,720);
	 JLabel emptyLabel = new JLabel("");
	 guiFrame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
	 
	 //This will center the JFrame in the middle of the screen
	 guiFrame.setLocationRelativeTo(null);
	 
	 guiFrame.setVisible(true);
    }
}