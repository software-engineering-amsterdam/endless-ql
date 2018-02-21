<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/gui/Radio_Buttons.java
package org.uva.forcepushql.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Radio_Buttons extends JPanel implements ActionListener {
	
	public Radio_Buttons(){
		
	}
	
	public void Create_Radio()
	{
		
		JRadioButton test_button = new JRadioButton("Test1"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test1");
		test_button.setSelected(true);
		
		JRadioButton test_button2 = new JRadioButton("Test2"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test2");
		
		JRadioButton test_button3 = new JRadioButton("Test3"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test3");
		
		ButtonGroup group = new ButtonGroup();
		group.add(test_button);
		group.add(test_button2);
		group.add(test_button3);
		
		test_button.addActionListener(this);
		test_button2.addActionListener(this);
		test_button3.addActionListener(this);

		JPanel radio_Panel = new JPanel(new GridLayout(0, 1));
		radio_Panel.add(test_button);
		radio_Panel.add(test_button2);
		radio_Panel.add(test_button3);
		
		add(radio_Panel, BorderLayout.LINE_START);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 	e.getActionCommand();
	 }
	
}
=======
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Radio_Buttons extends JPanel implements ActionListener {
	
	public Radio_Buttons(){
		
	}
	
	public void Create_Radio()
	{
		
		JRadioButton test_button = new JRadioButton("Test1"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test1");
		test_button.setSelected(true);
		
		JRadioButton test_button2 = new JRadioButton("Test2"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test2");
		
		JRadioButton test_button3 = new JRadioButton("Test3"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test3");
		
		ButtonGroup group = new ButtonGroup();
		group.add(test_button);
		group.add(test_button2);
		group.add(test_button3);
		
		test_button.addActionListener(this);
		test_button2.addActionListener(this);
		test_button3.addActionListener(this);

		JPanel radio_Panel = new JPanel(new GridLayout(0, 1));
		radio_Panel.add(test_button);
		radio_Panel.add(test_button2);
		radio_Panel.add(test_button3);
		
		add(radio_Panel, BorderLayout.LINE_START);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 	e.getActionCommand();
	 }
	
}
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/gui/Radio_Buttons.java
