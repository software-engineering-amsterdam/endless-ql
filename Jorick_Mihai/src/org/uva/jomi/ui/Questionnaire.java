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

import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.models.Question;
import org.uva.jomi.ui.storage.Storage;
import org.uva.jomi.ui.storage.StorageFactory;
import org.uva.jomi.ui.storage.StorageFactory.StorageType;
import org.uva.jomi.ui.views.core.Frame;
import org.uva.jomi.ui.views.core.Panel;

public class Questionnaire implements ActionListener {
	
	private QLForm form;

	private Frame frame;
	
	private JMenuItem openQL;
	private JMenuItem storeAnswers;
	
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

		//Create Open QL menu
		openQL = new JMenuItem("Open QL",KeyEvent.VK_T);
		openQL.setName("openql");
		openQL.addActionListener(this);
		menu.add(openQL);
		
		//Create store answers menu
		storeAnswers = new JMenuItem("Store answers", KeyEvent.VK_S);
		storeAnswers.setName("Store answers");
		storeAnswers.addActionListener(this);
		menu.add(storeAnswers);
		

		this.frame.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.openQL)) {
			this.openQLFile();
		}
		if(e.getSource().equals(this.storeAnswers)) {
			this.storeAnswers();
		}
	}
	
	private void storeAnswers() {
		this.form.store();
	}

	private void removeAllPanels() {
		for(Panel panel : this.panels) {
			this.frame.remove(panel);
		}
	}

	private void showForm(QLForm form) {		
		this.removeAllPanels();
		Panel mainPanel = new Panel();
		
		this.panels = form.getPanels();
		for(Panel panel : this.panels) {
			mainPanel.add(panel);
		}
		
		this.frame.add(mainPanel);
		mainPanel.setVisible(true);
		this.frame.setVisible(true);
	}
	
	private void openQLFile() {
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("QL file", "ql");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	
        		this.form = new QLForm(chooser.getSelectedFile().getAbsolutePath());
        		this.showForm(this.form);
        }
	}
}
