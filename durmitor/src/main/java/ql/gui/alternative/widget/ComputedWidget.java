package ql.gui.alternative.widget;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import ql.ast.expression.literal.Literal;
import ql.ast.statement.Question;

public class ComputedWidget extends Widget<JLabel> {

	public ComputedWidget(Question question) {
		
		component = new JLabel();
		component.setBorder(new EmptyBorder(5,0,0,0));
		component.setName(question.getIdentifier().getName());
		
		setValue(question.getIdentifier().getValue());
	}
	
	@Override
	public void setValue(Literal<?> value) {
		component.setText(value.getValue().toString());
	}

	@Override
	public String getValue() {
		return component.getText();
	}
}
