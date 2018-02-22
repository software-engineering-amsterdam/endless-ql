package org.uva.jomi.ui.elements;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.uva.jomi.ql.ast.statements.QuestionStmt;

public class QuestionElement implements BaseElement {

	private QuestionStmt question;
	
	public QuestionElement(QuestionStmt question) {
		this.question = question;
	}

	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		System.out.println("Question");
		panel.add(new JLabel(this.question.label));
		
		return panel;
	}
	
}
