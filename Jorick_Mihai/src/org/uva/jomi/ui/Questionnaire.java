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
import javax.swing.filechooser.FileNameExtensionFilter;

import org.uva.jomi.ui.storage.StorageFactory.StorageType;
import org.uva.jomi.ui.views.core.Frame;
import org.uva.jomi.ui.views.core.Panel;

public class Questionnaire implements ActionListener {
	
	private QLForm form;

	private Frame frame;
	
	private JMenuItem openQL;
	private JMenuItem storeAnswersTxt;
	private JMenuItem storeAnswersJson;
	
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
		storeAnswersTxt = new JMenuItem("Store answers to text", KeyEvent.VK_S);
		storeAnswersTxt.setName("Store answers to text");
		storeAnswersTxt.addActionListener(this);
		menu.add(storeAnswersTxt);
		
		//Create store answers menu
		storeAnswersJson = new JMenuItem("Store answers to json", KeyEvent.VK_S);
		storeAnswersJson.setName("Store answers to json");
		storeAnswersJson.addActionListener(this);
		menu.add(storeAnswersJson);
		

		this.frame.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.openQL)) {
			this.openQLFile();
		}
		if(e.getSource().equals(this.storeAnswersTxt)) {
			this.storeAnswers(StorageType.TEXT);
		}
		if(e.getSource().equals(this.storeAnswersJson)) {
			this.storeAnswers(StorageType.JSON);
		}
	}
	
	private void storeAnswers(StorageType type) {
		this.form.store(type);
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
