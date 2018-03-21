package ql.gui.alternative;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ql.ast.statement.Question;
import ql.gui.alternative.widget.Widget;

public class QuestionPanel extends JPanel {

	private static final long serialVersionUID = -2670862666930501658L;
	
	private Widget<?> widget;
	
	public QuestionPanel(Question question) {
		
		JLabel label = new JLabel(question.getLabel());
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBorder(new EmptyBorder(4,0,0,0));
		add(label);
		
		widget = Widget.create(question);
		add(widget.getComponent());
		
		setLayout(new GridLayout(1,2));
		revalidate();
	}
	
	public Widget<?> getWidget() {
		return widget;
	}
}
