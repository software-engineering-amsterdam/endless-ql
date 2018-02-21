<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/gui/GUI_Main.java
package org.uva.forcepushql.gui;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import javax.swing.*;


public class GUI_Main 
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

=======
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

>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/gui/GUI_Main.java
