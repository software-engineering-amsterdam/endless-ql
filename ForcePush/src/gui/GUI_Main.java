package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI_Main 
{
	 

	public static void main(String[] args) 
	{
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
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
	Radio_Buttons radio = new Radio_Buttons();

	guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	guiFrame.setTitle("Questionnaire");
	guiFrame.setSize(1280,720);

	
	
	JComponent newContentPane = radio;
	newContentPane.setOpaque(true);
	guiFrame.setContentPane(newContentPane);

	guiFrame.setLocationRelativeTo(null);
	guiFrame.setVisible(true);
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 	e.getActionCommand();
	 }

	
}

