package gui;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
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

