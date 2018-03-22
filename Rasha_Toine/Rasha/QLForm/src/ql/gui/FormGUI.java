package ql.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class FormGUI {
	
	//list of questions
	private List<QuestionGUI> questions;

	// constructor
	public FormGUI(List<QuestionGUI> questions) {
		this.questions = questions;
	}

	public List<QuestionGUI> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	// rendering method
	public void render() {
		JFrame frame = new JFrame();
		JPanel mainPanel = new JPanel();
		//JFileChooser fileChooser = new JFileChooser();
		JComponent formPane;
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		for (QuestionGUI q : getQuestions()){
			JComponent questionPanel = q.getJComponent();
			mainPanel.add(questionPanel);
			mainPanel.add(Box.createRigidArea(new Dimension(0,2)));
		}
		JScrollPane scrollPane = new JScrollPane(mainPanel);

		formPane = Box.createHorizontalBox();
		formPane.add(Box.createHorizontalGlue());
		formPane.add(scrollPane);
		formPane.add(Box.createHorizontalGlue());

		frame.setSize(800, 1000);
		frame.setBackground(Color.WHITE);
		frame.setContentPane(formPane);
		frame.setAutoRequestFocus(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//fileChooser.showOpenDialog(frame);
	}
}