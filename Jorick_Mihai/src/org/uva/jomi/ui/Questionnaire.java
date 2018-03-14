package org.uva.jomi.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.uva.jomi.ui.elements.core.Frame;
import org.uva.jomi.ui.elements.core.Panel;

public class Questionnaire implements ActionListener {

	private Frame frame;
	
	private JMenuItem openQL;
	
	private List<Panel> panels = new ArrayList<Panel>();
	
	public Questionnaire() {
		this.frame = new Frame();
		
		this.createMenu();
	}
	
	public void setVisibility(boolean visible) {
		this.frame.setVisible(visible);
	}
	
	private void createMenu() {
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		//Build the first menu.
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("");
		menuBar.add(menu);

		//a group of JMenuItems
		openQL = new JMenuItem("Open QL",KeyEvent.VK_T);
		openQL.setName("openql");
		openQL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		openQL.addActionListener(this);
		menu.add(openQL);

		this.frame.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.openQL)) {
			this.openQLFile();
		}
	}
	
	private void removeAllPanels() {
		for(Panel panel : this.panels) {
			this.frame.remove(panel);
		}
	}

	private void showForm(QLForm form) {
		this.removeAllPanels();
		
		this.panels = form.getPanels();
		for(Panel panel : this.panels) {
			this.frame.add(panel);
			panel.setVisible(true);
		}
		this.frame.setVisible(true);
	}
	
	private void openQLFile() {
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("QL file", "ql");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	
        		QLForm form = new QLForm(chooser.getSelectedFile().getAbsolutePath());
        		this.showForm(form);
        }
	}
}
